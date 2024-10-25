package com.example.umg_moto_xpress_android.ui.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.FragmentHomeBinding;
import com.example.umg_moto_xpress_android.dialog.OptionMenuDialog;
import com.example.umg_moto_xpress_android.dialog.bottomSheet.BottomDialogPayment;
import com.example.umg_moto_xpress_android.models.data.CarouselHomeData;
import com.example.umg_moto_xpress_android.models.data.UserDecodeData;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;
import com.example.umg_moto_xpress_android.ui.biker.ListBikerFragment;
import com.example.umg_moto_xpress_android.ui.carousel.CarouselFragment;
import com.example.umg_moto_xpress_android.viewmodel.BikerListViewModel;

import java.util.List;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private BikerListViewModel bikerListViewModel;
    private ListBikerFragment  listBikerFragment;
    private List<CarouselHomeData> carouselHomeDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hiddenShowNavBar(false);
        bikerListViewModel = new ViewModelProvider(requireActivity()).get(BikerListViewModel.class);
        Toast.makeText(requireActivity(), "Bienvenido " + getUserDecodeData().getName().toUpperCase(), Toast.LENGTH_LONG).show();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        functionFocusFragment(binding.getRoot());
        logoutLogin(binding.getRoot());
        onBackPressedCall(null);
        carouselHomeDataList = bikerListViewModel.createListCarouselHome(requireActivity());

        if (savedInstanceState == null) {
            Fragment carousel = new CarouselFragment(true,false,false,carouselHomeDataList,null);
            addChildFragmentManager(carousel,binding.carousel.getId());
        }
        getServiceBikerDis(savedInstanceState);


        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.reservation){
                clearOnBackPressedCall();
                navigation(binding.getRoot(),R.id.action_homeFragment_to_reservationBikerFragment);
        /*        BottomDialogPayment bottomSheetFragment = new BottomDialogPayment();
                bottomSheetFragment.show(requireActivity().getSupportFragmentManager(), "card_bottom");*/

            }else if (item.getItemId() == R.id.process){
                showDialogMenu(binding.getRoot());
            }
            return false;
        });

        return binding.getRoot();
    }


    private void showDialogMenu(View view) {
        OptionMenuDialog dialog = new OptionMenuDialog(num -> {
            switch (num){
                case 1:
                    clearOnBackPressedCall();
                    navigation(binding.getRoot(),R.id.action_homeFragment_to_profileUserFragment);
                    break;
                case 2:
                    clearOnBackPressedCall();
                    navigation(binding.getRoot(),R.id.action_homeFragment_to_reservationBikerFragment);
                    break;
                case 3:
                    clearOnBackPressedCall();
                    navigation(binding.getRoot(),R.id.action_homeFragment_to_inventarioBikerFragment);
                    break;
                case 4:
                    clearOnBackPressedCall();
                    navigation(binding.getRoot(),R.id.action_homeFragment_to_createBikerFragment);
                    break;
                case 5:
                    clearOnBackPressedCall();
                    navigation(binding.getRoot(),R.id.action_homeFragment_to_usersListFragment);
                    break;
                case 6:
                    clearOnBackPressedCall();
                    navigation(binding.getRoot(),R.id.action_homeFragment_to_informeBikerFragment);
                    break;
            }
        });
        dialog.show(getChildFragmentManager(), OptionMenuDialog.TAG);
    }

    private void getServiceBikerDis(Bundle savedInstanceState){
        if (bikerListViewModel.getBikerListDisponibles() == null){
            bikerListViewModel.getBikerListDisponibles(false);
            loadingShow(true);
        }

        sleepService(() -> {
            bikerListViewModel.getBikerListDisponibles().observe(requireActivity(), response -> {
                try {
                    if (savedInstanceState == null) {
                        listBikerFragment = new ListBikerFragment(response,true,false,false,0,item -> {
                            clearOnBackPressedCall();
                            navigation(binding.getRoot(), R.id.action_homeFragment_to_detailsBikerFragment);
                        });
                        addChildFragmentManagerDelete(listBikerFragment,binding.fragmentList.getId());
                    }
                    loadingShow(false);
                }catch (Exception e){
                    dialogMessage(getString(R.string.title_error),getString(R.string.message_error),2);
                    loadingShow(false);
                }
            });
        },500);


    }

}