package com.besplatka.test;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    public void createPoster(Poster poster, OnUpdatePosterCallback onUpdatePosterCallback) {
        DatabaseReference add = mDatabaseReference.push();
        add.setValue(new FirebasePoster(poster.getTitle(),
                poster.getDescription(),
                poster.getName(),
                poster.getPhone(),
                poster.getCost())
        );
    }

    @Override
    public void updatePoster(Poster poster, final OnUpdatePosterCallback onUpdatePosterCallback) {
        mDatabaseReference.child(poster.getId()).setValue(
                new FirebasePoster(poster.getTitle(),
                        poster.getDescription(),
                        poster.getName(),
                        poster.getPhone(),
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

}
