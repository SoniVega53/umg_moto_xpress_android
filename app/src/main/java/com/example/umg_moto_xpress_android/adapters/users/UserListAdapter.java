package com.example.umg_moto_xpress_android.adapters.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umg_moto_xpress_android.databinding.ItemUserDetailsBinding;
import com.example.umg_moto_xpress_android.models.data.UserDataModel;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    private List<UserDataModel> userDataModelList;

    public UserListAdapter(List<UserDataModel> userDataModelList) {
        this.userDataModelList = userDataModelList;
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserDetailsBinding binding = ItemUserDetailsBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        holder.bind(userDataModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return userDataModelList != null ? userDataModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemUserDetailsBinding binding;

        public ViewHolder(@NonNull ItemUserDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(UserDataModel item){
            binding.txtUserName.setText(item.getUserName());
            binding.txtEmail.setText(item.getEmail());
            binding.txtName.setText(item.getName());
            binding.txtLastname.setText(item.getLastname());
        }
    }
}
