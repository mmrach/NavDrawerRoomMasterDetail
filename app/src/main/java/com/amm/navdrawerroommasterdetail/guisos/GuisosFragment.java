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
import android.widget.ImageButton;

import java.util.List;

import navdrawerrecyclerviewroommasterdetail.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuisosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuisosFragment extends Fragment implements View.OnClickListener {

    private ViewModelProvider.AndroidViewModelFactory theAppFactory;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Objetos de Interfaz
    ImageButton ibCarnes, ibPescados, ibVerduras;
    RecyclerView rvGuisos;

    private GuisosViewModel guisosViewModel;
    private GuisosAdapter guisosAdapter;

    //Creamos un observador de guisos global
    Observer<List<Guisos>> guisosObserver = new Observer<List<Guisos>>() {
        @Override
        public void onChanged(List<Guisos> guisos) {
            if (guisos!=null && guisos.size()>0) {
                guisosAdapter.submitList(guisos);
            }
        }
    };

    public GuisosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuisosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuisosFragment newInstance(String param1, String param2) {
        GuisosFragment fragment = new GuisosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //Creamos el ViewModel que implicitamente integra el GuisosRepository
        guisosViewModel = new ViewModelProvider(this).get(GuisosViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guisos, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ibCarnes = (ImageButton) getView().findViewById(R.id.ibCarnes);
        ibCarnes.setOnClickListener(this);
        ibPescados = (ImageButton) getView().findViewById(R.id.ibPescados);
        ibPescados.setOnClickListener(this);
        ibVerduras = (ImageButton) getView().findViewById(R.id.ibVerduras);
        ibVerduras.setOnClickListener(this);

        rvGuisos = (RecyclerView) getView().findViewById(R.id.rvGuisosDe);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvGuisos.setLayoutManager(layoutManager);

        //En el GuisosAdapter queremos usar también el GuisosViewModel por eso le pasamos
        //una referencia a this Fragment, para que pueda ponerla en el ViewModelProvider al instanciarlo
        guisosAdapter = new GuisosAdapter(Guisos.guisosItemCallback,this);
        rvGuisos.setAdapter(guisosAdapter);

        //Observamos en el constructor el livedata del viewmodel con el obervador global
        guisosViewModel.ldGuisos.observe(getViewLifecycleOwner(),guisosObserver);

    }

    @Override
    public void onClick(View view) {
        ImageButton ib = (ImageButton) view;
        switch(ib.getId()) {
            case R.id.ibCarnes:
                // Do something
                guisosViewModel.queryGuisosByType(TiposGuisos.ID_GUISOS_DE_CARNE);
                guisosViewModel.ldGuisos.observe(getViewLifecycleOwner(),guisosObserver);
                break;
            case R.id.ibPescados:;
                // Do something
                guisosViewModel.queryGuisosByType(TiposGuisos.ID_GUISOS_DE_PESCADO);
                guisosViewModel.ldGuisos.observe(getViewLifecycleOwner(),guisosObserver);
                break;
            case R.id.ibVerduras:;
                // Do something
                guisosViewModel.queryGuisosByType(TiposGuisos.ID_GUISOS_DE_VERDURA);
                guisosViewModel.ldGuisos.observe(getViewLifecycleOwner(),guisosObserver);
                break;
        }
    }
}