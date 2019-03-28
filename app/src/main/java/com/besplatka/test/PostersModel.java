package com.besplatka.test;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.PhoneNumberUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostersModel implements IPostersModel {
    private static PostersModel mPostersModel;

    public static PostersModel getInstance() {
        if (mPostersModel == null) {
            mPostersModel = new PostersModel();
        }
        return mPostersModel;
    }

    private DatabaseReference mDatabaseReference;

    private PostersModel() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("posters");
    }

    @Override
    public void getPosters(final OnGetPostersCallback onPostersCallback) {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Poster> posterList = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    FirebasePoster firebasePoster = child.getValue(FirebasePoster.class);
                    if (firebasePoster != null) {
                        posterList.add(new Poster(child.getKey(), firebasePoster.getTitle(),
                                firebasePoster.getDescription(),
                                firebasePoster.getName(),
                                firebasePoster.getPhone(),
                                firebasePoster.getCity(),
                                firebasePoster.getCost())
                        );
                    }
                }
                onPostersCallback.onSuccess(posterList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
                onPostersCallback.onFailure(databaseError.getMessage());
            }
        });
    }

    @Override
    public void createPoster(Poster poster, final OnUpdatePosterCallback onUpdatePosterCallback) {
        if (!isValidPoster(poster, onUpdatePosterCallback)) {
            return;
        }

        DatabaseReference add = mDatabaseReference.push();
        add.setValue(new FirebasePoster(poster.getTitle(),
                        poster.getDescription(),
                        poster.getName(),
                        poster.getPhone(),
                        poster.getCity(),
                        poster.getCost()),
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            onUpdatePosterCallback.onSuccess();
                        } else {
                            databaseError.toException().printStackTrace();
                            onUpdatePosterCallback.onFailure(databaseError.getMessage());
                        }
                    }
                }
        );
    }

    @Override
    public void updatePoster(Poster poster, final OnUpdatePosterCallback onUpdatePosterCallback) {
        if (!isValidPoster(poster, onUpdatePosterCallback)) {
            return;
        }

        mDatabaseReference.child(poster.getId()).setValue(
                new FirebasePoster(poster.getTitle(),
                        poster.getDescription(),
                        poster.getName(),
                        poster.getPhone(),
                        poster.getCity(),
                        poster.getCost()),
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            onUpdatePosterCallback.onSuccess();
                        } else {
                            databaseError.toException().printStackTrace();
                            onUpdatePosterCallback.onFailure(databaseError.getMessage());
                        }
                    }
                }
        );
    }

    @Override
    public void removePoster(Poster poster, final OnUpdatePosterCallback onUpdatePosterCallback) {
        mDatabaseReference.child(poster.getId()).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    onUpdatePosterCallback.onSuccess();
                } else {
                    databaseError.toException().printStackTrace();
                    onUpdatePosterCallback.onFailure(databaseError.getMessage());
                }
            }
        });
    }

    private boolean isValidPoster(Poster poster, OnUpdatePosterCallback onUpdatePosterCallback) {
        if (poster.getTitle().isEmpty()) {
            onUpdatePosterCallback.onFailure("Title must be not empty");
            return false;
        }
        if (poster.getDescription().isEmpty()) {
            onUpdatePosterCallback.onFailure("Description must be not empty");
            return false;
        }
        if (poster.getName().isEmpty()) {
            onUpdatePosterCallback.onFailure("Name must be not empty");
            return false;
        }
        if (!PhoneNumberUtils.isGlobalPhoneNumber(poster.getPhone())) {
            onUpdatePosterCallback.onFailure("Invalid phone number");
            return false;
        }
        if (poster.getCity().isEmpty()) {
            onUpdatePosterCallback.onFailure("City must be not empty");
            return false;
        }
        if (poster.getCost() == 0) {
            onUpdatePosterCallback.onFailure("Cost must be not empty");
            return false;
        }
        return true;
    }

}
