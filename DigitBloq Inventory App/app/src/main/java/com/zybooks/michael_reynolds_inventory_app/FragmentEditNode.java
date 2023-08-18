package com.zybooks.michael_reynolds_inventory_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentEditNode extends Fragment {

    private TextView nodeName;
    private TextView nodeAddress;
    private AppCompatButton submitButton;
    private AppCompatButton cancelButton;
    public FragmentCallback callback;


    public FragmentEditNode(FragmentCallback callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editnode, container, false);

        submitButton = view.findViewById(R.id.buttonSubmit);
        cancelButton = view.findViewById(R.id.buttonCancel);

        nodeName = view.findViewById(R.id.editTextNodeName);
        nodeAddress = view.findViewById(R.id.editTextNodeAddress);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onCreateNode();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onCancelCreate();
            }
        });

        return view;
    }

    public void onCreateNode(){
        String newName = this.nodeName.getText().toString().length() > 0 ? this.nodeName.getText().toString() : "New Node";
        String newAddress = this.nodeAddress.getText().toString().length() > 0 ? this.nodeAddress.getText().toString() : "New Address";;

        callback.onNodeCreated(new NodeModel(newName, newAddress, 0, 0));

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        this.getView().clearFocus();

        transaction.remove(this);
        transaction.commit();
    }

    public void onCancelCreate(){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        this.getView().clearFocus();

        transaction.remove(this);
        transaction.commit();
    }
}
