package lava_jato.app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import lava_jato.app.dao.Maindao;

public class UserViewModelFactory implements ViewModelProvider.Factory {

    private final Maindao maindao;

    public UserViewModelFactory(Maindao maindao) {
        this.maindao = maindao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(maindao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

