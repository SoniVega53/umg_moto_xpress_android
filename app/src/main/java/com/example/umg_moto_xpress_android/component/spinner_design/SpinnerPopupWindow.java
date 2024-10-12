package com.example.umg_moto_xpress_android.component.spinner_design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.umg_moto_xpress_android.R;
import com.example.umg_moto_xpress_android.databinding.SpinnerCardCustomBinding;
import com.example.umg_moto_xpress_android.databinding.SpinnerTextBinding;
import com.google.android.material.card.MaterialCardView;
import com.example.umg_moto_xpress_android.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint({"Recycle,CustomViewStyleable"})
public class SpinnerPopupWindow extends ConstraintLayout {
    private SpinnerCardCustomBinding binding;
    private PopupWindow popupWindow;
    private AttributesSpinner attributesSpinner;

    public SpinnerPopupWindow(@NonNull Context context) {
        super(context);
        initialize(context,null);
    }

    public SpinnerPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }

    public SpinnerPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context,attrs);
    }

    public SpinnerPopupWindow(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context,attrs);
    }

    public void initialize(Context context,AttributeSet attrs){
        binding = SpinnerCardCustomBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomAttributesSpinner);
        attributesSpinner = new AttributesSpinner();
        attributesSpinner.setTitle(a.getString(R.styleable.CustomAttributesSpinner_textTitleHide));

        binding.txtTitle.setText(attributesSpinner.getTitle());
        printSpinnerSelect();
    }

    /**
     * metodo para implementar la infrormacion al spinner
     */
    public void setImplementDataSpinner(Context context, AttributesSpinner attr,OnSelectItem selectItem) {
        this.attributesSpinner.setTitle(attr.isTitle());
        this.attributesSpinner.setHeightDialog(attr.getHeightDialog());
        this.attributesSpinner.setOptions(attr.isTitle() ? addTitleToList(attr.getOptions(), attr.getTitle()) : attr.getOptions());

        binding.cardSpinner.setOnClickListener(view -> {
            this.attributesSpinner.setOpenMenu(true);
            showWindowsMenu(context, view, selectItem);
            changeSelectItem();
        });
    }

    /**
     * metodo para mostrar el menu desplegable del spinner
     */
    public void showWindowsMenu(Context context, View view, OnSelectItem selectItem){
        View layout = layoutCreate(context,selectItem);

        popupWindow = new PopupWindow(layout,
                view.getWidth(),
                attributesSpinner.getHeightDialog() > 0 ? attributesSpinner.getHeightDialog() : ViewGroup.LayoutParams.WRAP_CONTENT ,
                true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        //valida si el spinner esta en una posicion muy baja de la pantalla
        if ((location[1] + view.getHeight() + popupWindow.getHeight() + 150) > screenHeight) {
            menuViewUp(layout,view);
        } else {
            popupWindow.showAsDropDown(view,0,0);
        }

        popupWindow.setOnDismissListener(() -> {
            attributesSpinner.setOpenMenu(false);
            changeSelectItem();
        });
    }

    /**
     * metodo para colocar el menu del espiner arriba del input
     */
    private void menuViewUp(View layout,View view){
        layout.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int popupHeight = this.attributesSpinner.getHeightDialog() > 0 ? this.attributesSpinner.getHeightDialog() : layout.getMeasuredHeight();
        int popupWidth = view.getWidth();

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int anchorX = location[0];
        int anchorY = location[1];

        int xOffset = anchorX + (popupWidth / 2) - (popupWidth / 2);
        int yOffset = (anchorY - popupHeight);

        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, xOffset, yOffset);
    }

    /**
     * metodo para hacer el cambio de vista del spinner al tener abierto el menu y al cerrarlo
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeSelectItem(){
        binding.cardSpinner.setStrokeColor(getResources().getColor(attributesSpinner.isOpenMenu() ? R.color.primaryGreen :R.color.primaryDarkGray ));
        binding.spinnerImage.setImageDrawable(getContext().getDrawable(attributesSpinner.isOpenMenu() ? R.drawable.baseline_keyboard_arrow_up_24:R.drawable.baseline_keyboard_arrow_down_24));
    }
    /**
     * crea una vista personalizada para el menu desplegable
     */
    private FrameLayout layoutCreate(Context context, OnSelectItem selectItemListener){
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setPadding(0,attributesSpinner.getSpaceMenu(),0,attributesSpinner.getSpaceMenu());

        MaterialCardView cardView = new MaterialCardView(context);
        cardView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        cardView.setCardElevation(2f);
        cardView.setRadius(24f);
        cardView.setStrokeWidth(2);
        cardView.setContentPadding(16, 16, 16, 16);
        cardView.setCardBackgroundColor(context.getResources().getColor(R.color.white));

        // Crear el ScrollView
        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        // Crear el LinearLayout
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundResource(R.color.white);
        linearLayout.setElevation(0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 0, 0, 0);
        linearLayout.setLayoutParams(layoutParams);

        createOptionText(context, linearLayout, selectItemListener);

        scrollView.addView(linearLayout);
        cardView.addView(scrollView);
        frameLayout.addView(cardView);
        return frameLayout;
    }
    /**
     * metodo que pinta los items del listado
     */
    private void createOptionText(Context context, LinearLayout linearLayout, OnSelectItem selectItemListener){
        for (String text:attributesSpinner.getOptions()) {
            LayoutInflater inflater = LayoutInflater.from(context);
            SpinnerTextBinding spiBin = SpinnerTextBinding.inflate(inflater);

            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            spiBin.getRoot().setLayoutParams(textViewParams);
            spiBin.getRoot().setOnClickListener(view -> {
                if (!attributesSpinner.isTitle() || attributesSpinner.getOptions().indexOf(text) > 0){
                    int pos = attributesSpinner.getOptions().indexOf(text);
                    if (selectItemListener != null)
                        selectItemListener.selectItem(text,(attributesSpinner.isTitle() ? pos - 1 : pos));
                    binding.txtOption.setText(text);
                    attributesSpinner.setTextItemSelect(text);
                    attributesSpinner.setPositionSelect(attributesSpinner.isTitle() ? pos - 1 : pos);
                    attributesSpinner.setSelectItem(true);

                    if (popupWindow != null)
                        popupWindow.dismiss();
                    printSpinnerSelect();
                }
            });
            spiBin.textt.setText(text);
            linearLayout.addView(spiBin.getRoot());
        }
    }

    /**
     * metodo que permite agregar al inicio un titulo al spinner
     */
    private List<String> addTitleToList(List<String> values, String title) {
        List<String> newVa = new ArrayList<>(values != null ? values : new ArrayList<>());
        newVa.add(0, title);
        return newVa;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void printSpinnerSelect(){
        binding.txtTitle.setGravity(attributesSpinner.isSelectItem() ? Gravity.TOP : Gravity.CENTER_VERTICAL);
        binding.txtTitle.setTextColor(getContext().getColor(attributesSpinner.isSelectItem() ? R.color.primaryDarkBlue : R.color.primaryDarkBlack));
        binding.txtOption.setTextColor(getContext().getColor(R.color.primaryDarkBlack));
        binding.txtOption.setVisibility(attributesSpinner.isSelectItem() ? VISIBLE : GONE);
        changeSelectItem();
    }

    public void setSelectPosition(int position){
        try {
            if (attributesSpinner.isTitle() && (position < attributesSpinner.getOptions().size() && position > -1)){
                binding.txtOption.setText(attributesSpinner.getOptions().get(position + 1));
                attributesSpinner.setTextItemSelect(attributesSpinner.getOptions().get(position + 1));
            }else {
                binding.txtOption.setText(attributesSpinner.getOptions().get(position));
                attributesSpinner.setTextItemSelect(attributesSpinner.getOptions().get(position));
            }
            attributesSpinner.setPositionSelect(position);
            attributesSpinner.setSelectItem(true);
            printSpinnerSelect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resetSelection(){
        attributesSpinner.resetAttributes();
        binding.txtOption.setText("");
        printSpinnerSelect();
    }

    public SpinnerCardCustomBinding getBinding() {
        return binding;
    }

    public AttributesSpinner getAttributesSpinner() {
        return attributesSpinner;
    }
}
