package com.example.umg_moto_xpress_android.ui.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentHomeBinding;
import com.example.umg_moto_xpress_android.dialog.message.DetailsMessageInf;
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

    @SuppressLint("NonConstantResourceId")
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
            focusFragment = true;
            dialogMessage(getString(R.string.logout),getString(R.string.logout_message),0,view1 -> {
                clearOnBackPressedCall();
                SharedPreferencesTool.writeSecureString(requireActivity(), StringTool.LOGIN_SESSION,"");
                Navigation.findNavController(binding.getRoot()).popBackStack(R.id.loginFragment,false);
            });
        });

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.home:
//                    // Acción para el item "Home"
//                    Toast.makeText(getActivity(), "Home seleccionado", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.reservation:
//                    // Acción para el item "Dashboard"
//                    Toast.makeText(getActivity(), "Dashboard seleccionado", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.process:
//                    // Acción para el item "Notifications"
//                    Toast.makeText(getActivity(), "Notifications seleccionado", Toast.LENGTH_SHORT).show();
//                    return true;
//            }

            return false;
        });

        return binding.getRoot();
    }


}