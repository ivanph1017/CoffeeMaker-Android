package com.eveexite.coffeemaker_android.data.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * Created by ivan on 6/1/17.
 */

public class RxFirebaseDatabase {

    @NonNull
    public static Flowable<DataSnapshot> flowableValueEvent(final Query query) {
        return Flowable.create(new FlowableOnSubscribe<DataSnapshot>() {
                                   @Override
                                   public void subscribe(FlowableEmitter<DataSnapshot> fe) throws Exception {
                                       final ValueEventListener valueEventListener = query.addValueEventListener(
                                               new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(DataSnapshot dataSnapshot) {
                                                       fe.onNext(dataSnapshot);
                                                   }

                                                   @Override
                                                   public void onCancelled(DatabaseError databaseError) {
                                                       fe.onError(new RxFirebaseDataException(databaseError));
                                                   }
                                               }
                                       );
                                       fe.setDisposable(Disposables.fromAction(new Action() {
                                           @Override
                                           public void run() throws Exception {
                                               query.removeEventListener(valueEventListener);
                                           }
                                       }));
                                   }
                               },
                BackpressureStrategy.BUFFER);
    }

    @NonNull
    public static Observable<DataSnapshot> observableSingleValueEvent(final Query query) {
        return Observable.create(new ObservableOnSubscribe<DataSnapshot>() {
            @Override
            public void subscribe(ObservableEmitter<DataSnapshot> oe) throws Exception {
                query.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                oe.onNext(dataSnapshot);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                oe.onError(new RxFirebaseDataException(databaseError));
                            }
                        }
                );
            }
        });
    }

    public static Flowable<RxFirebaseChildEvent<DataSnapshot>> flowableChildEvent(
            @NonNull final Query query) {
        return Flowable.create(new FlowableOnSubscribe<RxFirebaseChildEvent<DataSnapshot>>() {
                                   @Override
                                   public void subscribe(FlowableEmitter<RxFirebaseChildEvent<DataSnapshot>> fe) throws Exception {
                                       final ChildEventListener childEventListener = query.addChildEventListener(new ChildEventListener() {
                                           @Override
                                           public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                                               fe.onNext(new RxFirebaseChildEvent<>(dataSnapshot.getKey(),
                                                       dataSnapshot,
                                                       previousChildName,
                                                       RxFirebaseChildEvent.EventType.ADDED
                                               ));
                                           }

                                           @Override
                                           public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                                               fe.onNext(new RxFirebaseChildEvent<>(dataSnapshot.getKey(),
                                                       dataSnapshot,
                                                       previousChildName,
                                                       RxFirebaseChildEvent.EventType.CHANGED
                                               ));
                                           }

                                           @Override
                                           public void onChildRemoved(DataSnapshot dataSnapshot) {
                                               fe.onNext(new RxFirebaseChildEvent<>(dataSnapshot.getKey(),
                                                       dataSnapshot,
                                                       RxFirebaseChildEvent.EventType.REMOVED
                                               ));
                                           }

                                           @Override
                                           public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                                               fe.onNext(new RxFirebaseChildEvent<>(dataSnapshot.getKey(),
                                                       dataSnapshot,
                                                       previousChildName,
                                                       RxFirebaseChildEvent.EventType.MOVED
                                               ));
                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {
                                               fe.onError(new RxFirebaseDataException(databaseError));
                                           }
                                       });
                                       fe.setDisposable(Disposables.fromAction(new Action() {
                                           @Override
                                           public void run() throws Exception {
                                               query.removeEventListener(childEventListener);
                                           }
                                       }));
                                   }
                               },
                BackpressureStrategy.BUFFER
        );
    }

    @NonNull
    public static <T> Flowable<T> flowableValueEvent(@NonNull final Query query,
                                                     @NonNull final Function<? super DataSnapshot, ? extends T> mapper) {
        return flowableValueEvent(query).map(mapper);
    }

    @NonNull
    public static <T> Observable<T> observableSingleValueEvent(@NonNull final Query query,
                                                               @NonNull final Function<? super DataSnapshot, ? extends T> mapper) {
        return observableSingleValueEvent(query).map(mapper);
    }

    @NonNull
    public static <T> Flowable<RxFirebaseChildEvent<T>> flowableChildEvent(
            @NonNull final Query query, @NonNull final Function<? super RxFirebaseChildEvent<DataSnapshot>, ? extends RxFirebaseChildEvent<T>> mapper) {
        return flowableChildEvent(query).map(mapper);
    }

    @NonNull
    public static <T> Flowable<T> flowableValueEvent(@NonNull final Query query,
                                                     @NonNull final Class<T> clazz) {
        return flowableValueEvent(query, DataSnapshotMapper.of(clazz));
    }

    @NonNull
    public static <T> Observable<T> observableSingleValueEvent(@NonNull final Query query,
                                                               @NonNull final Class<T> clazz) {
        return observableSingleValueEvent(query, DataSnapshotMapper.of(clazz));
    }

    @NonNull
    public static <T> Flowable<RxFirebaseChildEvent<T>> flowableChildEvent(
            @NonNull final Query query, @NonNull final Class<T> clazz) {
        return flowableChildEvent(query, DataSnapshotMapper.ofChildEvent(clazz));
    }

}
