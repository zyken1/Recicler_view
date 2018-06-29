package com.example.nequiz_omen.recicler_view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nequiz_OMEN on 28/06/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{   /*  <------  De la clase MyAdapter  pasa el metodo  ViewHolder*/

    //lista de string
    private List<String> names;       //lista de nombres
    private int layout;                 //el layout que queremos renderizar  con el que queremos inflar la vista
    private  OnItemClickListener itemClickListener;           //este se declaro abajo una interfaz que se le pasa un nombre y una posicion



    //aqui va le constructor el que va a reciibir los parametros
    public MyAdapter(List<String> names, int layout, OnItemClickListener listener){
        this.names = names;
        this.layout = layout;
        this.itemClickListener = listener;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  /*Este metodo nos solicita un vieHolder necesariamente y ejecutar el codigo dentro*/
        /*return null;*/
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);  /* Le pasamos directamente la vista inflada  */
        ViewHolder vh = new ViewHolder(v);                                                      /* Se le pasa como constructor del ViewHolder   "v"  */
        return vh;
    }



    @Override    /*No se recomienda mandar a llamar la position aqui ya que no se actualizaria la posicion ya que se renderiza solo una vez*/
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.blind(names.get(position),itemClickListener);   /*al holder llama a su metodo  blind  y le pasa tener una lista = itemClickListener que es global*/
    }     /*itemClickListener se le dice como actuar dentro del   blind */



    @Override
    public int getItemCount() {
        //return 0;
        return names.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;        /*Este es le atributo name */

        public ViewHolder(View itemView) {  /*aqui va el constructor*/
            super(itemView);            /*Se le pasa la vista al padre */
            this.textViewName = itemView.findViewById(R.id.textViewName);    /*Extraemos la referencia y se guarda en el atributo name */
        }

      public void blind(final String name, final OnItemClickListener Listener){
            this.textViewName.setText(name);  //aqui s emanda allamar los nombres se puede modificar a objetos
                    /*Se puede implementar un ON LONG para meter eventos */
            itemView.setOnClickListener(new View.OnClickListener(){  /*El itemView  tiene una instancia para la vista entera */
                @Override
                public void onClick(View view) {
                    Listener.onItemClick(name, getPosition()); /*getAdapterPosition*/
                }
            });
      }//Aqui termina el blind
    }//AQUI SE IMPLEMENTA LA CLASE  VIEW HOLDER





    public interface OnItemClickListener {             /*que se va implementar al dar un click a esta lista*/
        void onItemClick(String name, int position);   /*Se coloca un Metodo al que llamaremos  onItemClick   PUEDE TENER OTOR NOMBRE*/
    }

}//end class my adapter
