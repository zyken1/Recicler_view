package com.example.nequiz_omen.recicler_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        private List<String> names;

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;    /*Se puede declarar como el extends que es RecyclerView.Adapter  o como el padre que se llama MyAdapter  ambos casos son acpetables*/
        private RecyclerView.LayoutManager mLayoutManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = this.getAllNames();  /*INSTANCIAMOS LA LISTA*/
        mRecyclerView = findViewById(R.id.recyclerView);  /*SE TRAE LA LISTA DESDE LA VISTA*/
        mLayoutManager = new LinearLayoutManager(this);  /*AQUI PUEDE SER LINEAR O  GRID O RELATIVE SE PUEDE CAMBIAR LA  VISTA*/
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {  /*names = es la lista , el layout = recycler_view_item y al no tener el listener se hace*/

            @Override
            public void onItemClick(String name, int position) {  /*Se sobre escribe este metodo para el listener  ya que se declaro la clase de MyAdapter */
                Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_SHORT).show();
            }
        });
        //se adapta  el RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);  /*Este recyclerView le pertenece a este LayoutManager */
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Alejandro");
            add("jose");
            add("Barrera");
            add("Ruben");
            add("Antonio");
        }};
    }

}//end on create
