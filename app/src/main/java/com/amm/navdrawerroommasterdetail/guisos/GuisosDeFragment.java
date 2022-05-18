package com.amm.navdrawerroommasterdetail.guisos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import navdrawerrecyclerviewroommasterdetail.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuisosDeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuisosDeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TIPO_GUISO = "ARG_TIPO_GUISO";

    // TODO: Rename and change types of parameters
    private Integer mTipoGuiso;

    private RecyclerView rvGuisos;
    private GuisosAdapter guisosAdapter;
    private GuisosViewModel guisosViewModel;

    //Creamos un observador de guisos global
    Observer<List<Guisos>> guisosObserver = new Observer<List<Guisos>>() {
        @Override
        public void onChanged(List<Guisos> guisos) {
            if (guisos!=null && guisos.size()>0) {
                guisosAdapter.submitList(guisos);
            }
        }
    };

    public GuisosDeFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment GuisosDeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static GuisosDeFragment newInstance(String param1, String param2) {
//        GuisosDeFragment fragment = new GuisosDeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTipoGuiso = getArguments().getInt(ARG_TIPO_GUISO);
        }

        //Creamos el ViewModel que implicitamente integra el GuisosRepository
        guisosViewModel = new ViewModelProvider(this).get(GuisosViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guisos_de, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivGuisosDe = getView().findViewById(R.id.ivGuisosDe);

        rvGuisos = (RecyclerView) getView().findViewById(R.id.rvGuisosDe);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvGuisos.setLayoutManager(layoutManager);

        //En el GuisosAdapter queremos usar también el GuisosViewModel por eso le pasamos
        //una referencia a this Fragment, para que pueda ponerla en el ViewModelProvider al instanciarlo
        guisosAdapter = new GuisosAdapter(Guisos.guisosItemCallback,this);
        rvGuisos.setAdapter(guisosAdapter);

        if (mTipoGuiso == TiposGuisos.ID_GUISOS_DE_CARNE) {
            ivGuisosDe.setImageResource(R.drawable.carnes);
            guisosViewModel.queryGuisosByType(TiposGuisos.ID_GUISOS_DE_CARNE);
        }
        else if (mTipoGuiso == TiposGuisos.ID_GUISOS_DE_PESCADO){
            ivGuisosDe.setImageResource(R.drawable.pescados);
            guisosViewModel.queryGuisosByType(TiposGuisos.ID_GUISOS_DE_PESCADO);
        }
        else if (mTipoGuiso == TiposGuisos.ID_GUISOS_DE_VERDURA){
            ivGuisosDe.setImageResource(R.drawable.verduras);
            guisosViewModel.queryGuisosByType(TiposGuisos.ID_GUISOS_DE_VERDURA);
        }
        guisosViewModel.ldGuisos.observe(getViewLifecycleOwner(),guisosObserver);

    }
}