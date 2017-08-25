package com.eveexite.coffeemaker_android.ui.presenter;

import android.widget.ImageView;

import com.eveexite.coffeemaker_android.di.annotations.PerActivity;
import com.eveexite.coffeemaker_android.domain.model.CoffeeMaker;
import com.eveexite.coffeemaker_android.domain.usecase.GetCoffeeMaker;
import com.eveexite.coffeemaker_android.domain.usecase.UseCaseSubscription;
import com.eveexite.coffeemaker_android.ui.base.BasePresenter;
import com.eveexite.coffeemaker_android.ui.model.CoffeeMakerUiModel;
import com.eveexite.coffeemaker_android.ui.model.mapper.CoffeeMakerUiToCoffeeMakerMapper;

import javax.inject.Inject;

/**
 * Created by Ivan on 3/06/2017.
 */

@PerActivity
public class MainPresenter extends BasePresenter<MainPresenter.View>{

    private GetCoffeeMaker getCoffeeMaker;
    private CoffeeMakerUiToCoffeeMakerMapper mapper;
    private CoffeeMakerUiModel coffeeMakerUiModel;

    @Inject
    MainPresenter(GetCoffeeMaker getCoffeeMaker, CoffeeMakerUiToCoffeeMakerMapper mapper) {
        this.getCoffeeMaker = getCoffeeMaker;
        this.mapper = mapper;
    }

    @Override
    public void initialize() {
        super.initialize();
        getCoffeeMaker.execute(new CoffeeMakerSubscription());
    }

    public void destroy() {
        getCoffeeMaker.dispose();
        setView(null);
    }

    public void showMsgConfirmation() {
        if(coffeeMakerUiModel == null) {
            getView().showMessage("Aviso", "No hay datos de la cafetera. Presione nuevamente el botón de encender.");
        } else {
            getView().showMsgConfirmation("Aviso", coffeeMakerUiModel.getTurnOn() ?
                    "Deseas apagar la cafetera?" : "Deseas encender la cafetera?");
        }
    }
    
    public void isCoffeeMakerReady() {
        if(coffeeMakerUiModel.getCoffeeMakerReady()) {
            if(coffeeMakerUiModel.getWaterLevel() > 0) {
                if(!coffeeMakerUiModel.getTurnOn()) {
                    //Encender cafetera
                    coffeeMakerUiModel.setTurnOn(true);
                    getCoffeeMaker.addUseCase(mapper.map(coffeeMakerUiModel));
                } else {
                    getView().showMessage("Error", "Cafetera ya está encendida!");
                }
            } else {
                getView().showMessage("Error", "No hay suficiente agua.");
            }
        } else {
            if(coffeeMakerUiModel.getTurnOn()) {
                //Apagar cafetera
                coffeeMakerUiModel.setTurnOn(false);
                getCoffeeMaker.addUseCase(mapper.map(coffeeMakerUiModel));
            } else {
                getView().showMessage("Error", coffeeMakerUiModel.getTimerSleep() == null ?
                        "Cafetera descansando. Espera " + "5"  +  " minutos por favor :)" :
                        "Cafetera descansando. Espera " + coffeeMakerUiModel.getTimerSleep()  +  " minutos por favor :)");
            }

        }
    }

    public void setCoffeeMakerUiModel(CoffeeMakerUiModel coffeeMakerUiModel) {
        this.coffeeMakerUiModel = coffeeMakerUiModel;
    }

    public interface View extends BasePresenter.BaseView {

        void showTitle(String title);

        void showWaterLevelValue(int waterLevel);

        void showTimerValue(String timer);

        void setAnimation(String filePath, ImageView.ScaleType scaleType);

        void playAnimation();

        void showMsgConfirmation(String title, String message);

    }

    private final class CoffeeMakerSubscription extends UseCaseSubscription<CoffeeMaker> {
        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(CoffeeMaker coffeeMaker) {
            CoffeeMakerUiModel temp = coffeeMakerUiModel;
            setCoffeeMakerUiModel(mapper.reverseMap(coffeeMaker));
            if(coffeeMakerUiModel.getCoffeeMakerReady()) {
                if(coffeeMakerUiModel.getWaterLevel() > 0) {
                    if(!coffeeMakerUiModel.getTurnOn()) {
                        getView().showTitle("Cafetera lista para encender.");
                        getView().setAnimation("coffee_maker.json", ImageView.ScaleType.FIT_XY);
                    }
                } else {
                    getView().showTitle("No hay suficiente agua.");
                    getView().setAnimation("coffee_maker.json", ImageView.ScaleType.FIT_XY);
                }

            } else {
                if(coffeeMakerUiModel.getTurnOn()) {
                    if(coffeeMakerUiModel.getCoffeeReady()) {
                        if(temp.getCoffeeReady() != coffeeMakerUiModel.getCoffeeReady()) {
                            getView().showTitle("Café listo");
                            getView().setAnimation("coffee_cup.json", ImageView.ScaleType.FIT_CENTER);
                            getView().playAnimation();
                        }
                    } else {
                        if(temp.getCoffeeMakerReady() != coffeeMakerUiModel.getCoffeeMakerReady()) {
                            getView().showTitle("Preparando café. Sé paciente por favor :)");
                            getView().setAnimation("coffee_maker.json", ImageView.ScaleType.FIT_XY);
                            getView().playAnimation();
                        }
                    }

                } else {
                    getView().showTitle(coffeeMakerUiModel.getTimerSleep() == null ?
                            "Cafetera descansando. Espera " + "5"  +  " minutos por favor :)" :
                            "Cafetera descansando. Espera " + coffeeMakerUiModel.getTimerSleep()  +  " minutos por favor :)");
                    getView().setAnimation("coffee_maker.json", ImageView.ScaleType.FIT_XY);
                }

            }
            getView().showTimerValue(coffeeMakerUiModel.getTimer() == null ? "00:00 mm:ss"  : coffeeMakerUiModel.getTimer() + " mm:ss");
            getView().showWaterLevelValue(coffeeMakerUiModel.getWaterLevel());
        }
    }
}
