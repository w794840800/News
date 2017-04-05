package comn.example.administrator.news.callback;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

import comn.example.administrator.news.jean.qiushibaike;

class MyDifficult extends DiffUtil.Callback{
        ArrayList<qiushibaike>newList,oldList;

public MyDifficult(ArrayList<qiushibaike> newList1,
                   ArrayList<qiushibaike>oldList1){
    newList=newList1;
    oldList=oldList1;


}
        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
  if (oldList.get(oldItemPosition).getUrl().equals(newList.get(newItemPosition).getTitle())){
      return true;
  }
else {
      return false;
  }  }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
           if (oldList.get(oldItemPosition).getPicurl().equals(newList.get(newItemPosition).getPicurl())){

               return true;

           }
   else {
               return false;
           }}
    }