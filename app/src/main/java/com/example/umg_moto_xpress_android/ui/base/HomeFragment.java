package com.example.umg_moto_xpress_android.ui.base;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentHomeBinding;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.ui.biker.ListBikerFragment;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hiddenShowNavBar(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());

        if (savedInstanceState == null) {
            Fragment newFragment = new ListBikerFragment();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.fragmentList, newFragment) // Usa el ID de tu FrameLayout
                    .commit();
        }

        onBackPressedCall(null);

        logout(view -> {
            clearOnBackPressedCall();
            SharedPreferencesTool.writeSecureString(requireActivity(), StringTool.LOGIN_SESSION,"");
            Navigation.findNavController(binding.getRoot()).popBackStack(R.id.loginFragment,false);
        });

        return binding.getRoot();
    }


}