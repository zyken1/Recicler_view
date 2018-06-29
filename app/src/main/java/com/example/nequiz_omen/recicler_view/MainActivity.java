package com.example.nequiz_omen.recicler_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        private List<String> names;

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;    /*Se puede declarar como el extends que es RecyclerView.Adapter  o como el padre que se llama MyAdapter  ambos casos son acpetables*/
        private RecyclerView.LayoutManager mLayoutManager;

        private int counter  = 0 ;   // se crea un contador para le metodo AddName  simplemente para ir oambiando el nombre

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = this.getAllNames();  /*INSTANCIAMOS LA LISTA*/
        mRecyclerView = findViewById(R.id.recyclerView);             /*SE TRAE LA LISTA DESDE LA VISTA*/
        mLayoutManager = new LinearLayoutManager(this);      /*AQUI PUEDE SER LINEAR O  GRID O RELATIVE SE PUEDE CAMBIAR LA  VISTA*/
        mLayoutManager = new GridLayoutManager(this,2);     //SE CAMBIA LA VISTA Y TE PIDE UN NUMERO DE COLUMNAS QUE SERAN 2
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);  // Esta vista  te permite multiples layouts y los renderiza para una mejor vista


        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {  /*names = es la lista , el layout = recycler_view_item y al no tener el listener se hace*/

            @Override
            public void onItemClick(String name, int position) {  /*Se sobre escribe este metodo para el listener  ya que se declaro la clase de MyAdapter */
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_SHORT).show();
                deleteName(position);  //se llama el metodo para  eliminar
            }
        });

         mRecyclerView.setHasFixedSize(true);   //mejora le performance del recicler view  si no cmabia de tamaño el recicler view
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());  //Implementacion de una animacion en el recyclerView

        //se adapta  el RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);  /*Este recyclerView le pertenece a este LayoutManager */
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override   //aqui se crean los ITEM
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater  = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_name:
                    this.addName(0);  //se crea el metodo al que llamaremos addName  y se le pasa una posicion la cual sera  CERO
                return true;
            default:
                //here code code
               return super.onOptionsItemSelected(item);
        }

    }

     private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Alejandro");
            add("jose");
            add("Barrera");
            add("Ruben");
            add("Antonio");
        }};
    }//end getAllName


    private void addName(int position) {
         names.add(position, "New Name" + (++ counter));
         mAdapter.notifyItemInserted(position);    //Tenemos que decirle al adaptador que algo ha cambiado como se le hizo al ListView
        mLayoutManager.scrollToPosition(position);  //CON ESTE METODO SIEMPRE NOS PONDRA EN LA ULTIMA POSIICON AÑADIDA
    }

    private void deleteName(int position){   /*Este metodo es para  eliminar objeto sy notiifcarle al adapter que s ea eliminado algo */
         names.remove(position);
         mAdapter.notifyItemRemoved(position);

    }
}//end on create
