# CoffeeMaker

This is part of a project to manage a coffee maker at home from a Android mobile phone. It's made up of an Android app written in Java as the front-end, Firebase service as the back-end and NodeMCU as a microcontroller written  in C/C++ and integrated to a coffee maker with water level sensors.

The coffee maker can be turned on/off by the Android app. Nonetheless, it can only be kept turned on maximum of 1 hour. If such an amount of time has passed, it automatically turns off and it rests up by 5 minutes in order to be available of turning on again.

Also, there is a couple of float ball liquid sensors in the cold water tank monitoring the water levels. If there is not enough water, the coffee maker cannot be turned on by the Android app. So then, it shows an error message on this issue.

## Project arquitecture / Arquitectura del proyecto

![Project arquitecture](https://raw.githubusercontent.com/ivanph1017/AssetsRepo/master/CoffeeMaker/arquitecture%20project.png)

-	[CoffeeMaker-NodeMCU project](https://github.com/ivanph1017/CoffeeMaker-NodeMCU)
-	[CoffeeMaker-Android project](https://github.com/ivanph1017/CoffeeMaker-Android)

<div style="width:90%;margin:auto;">
    <img style="margin:20px;" src="https://raw.githubusercontent.com/ivanph1017/AssetsRepo/master/CoffeeMaker/intro.gif" alt="Intro" height="480" width="288"/>
    <span>
        <img style="margin:20px;"
        src="https://raw.githubusercontent.com/ivanph1017/AssetsRepo/master/CoffeeMaker/waterFilling.gif" alt="Water filling" height="480" width="288"/>
    </span>
    <span>
        <img style="margin:20px;"
        src="https://raw.githubusercontent.com/ivanph1017/AssetsRepo/master/CoffeeMaker/preparingCoffee.gif" alt="Preparing coffee" height="480" width="288"/>
    </span>
</div>

<div style="width:90%;margin:auto;">
    <img style="margin:20px;" src="https://raw.githubusercontent.com/ivanph1017/AssetsRepo/master/CoffeeMaker/coffeeReady.gif" alt="Coffee ready" height="480" width="288"/>
    <span>
        <img style="margin:20px;"
        src="https://raw.githubusercontent.com/ivanph1017/AssetsRepo/master/CoffeeMaker/coffeeMakerResting.gif" alt="Coffee ready" height="480" width="288"/>
  </span>
</div>

## CoffeeMaker-Android

The Android app was developed in Java following clean arquitecture, repository, factory and observer-subcriber patterns. More details can be found here: 

- [Clean arquitecure](https://erikcaffrey.github.io/ANDROID-clean-architecture/) by Erik Jhordan Rey
- [Arquitecting Android](https://fernandocejas.com/2015/07/18/architecting-android-the-evolution/) by Fernando Cejas
