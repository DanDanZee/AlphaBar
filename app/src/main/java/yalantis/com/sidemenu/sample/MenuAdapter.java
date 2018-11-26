package yalantis.com.sidemenu.sample;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends ArrayAdapter{
    private final int resourceId;
    private int currentItem = -1;
    public MenuAdapter(Context context, int textViewResourceId, List<MenuList> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MenuList menulist = (MenuList) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        LinearLayout ShowArea = (LinearLayout) view.findViewById(R.id.layout_showArea);
        RelativeLayout HideArea = (RelativeLayout) view.findViewById(R.id.layout_hideArea);
        ImageView ItemImage = (ImageView) view.findViewById(R.id.item_image);//获取该布局内的图片视图
        TextView ItemName = (TextView) view.findViewById(R.id.item_name);//获取该布局内的文本视图
        TextView ItemWhere = (TextView) view.findViewById(R.id.item_where);
        TextView ItemContent = (TextView) view.findViewById(R.id.item_content);
        TextView ItemElement = (TextView) view.findViewById(R.id.item_element);

        ItemImage.setImageResource(menulist.getImageId());//为图片视图设置图片资源
        ItemName.setText(menulist.getName());//为文本视图设置文本标题
        ItemWhere.setText(menulist.getWhereId());//为文本视图设置文本产地
        ItemContent.setText(menulist.getContent());//为文本视图设置文本产地
        ItemElement.setText(menulist.getElement());//为文本视图设置文本产地

        ShowArea.setTag(position);
        ShowArea.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //用 currentItem 记录点击位置
                int tag = (Integer) view.getTag();
                if (tag == currentItem) { //再次点击
                    currentItem = -1; //给 currentItem 一个无效值
                } else {
                    currentItem = tag;
                }
                //通知adapter数据改变需要重新加载
                notifyDataSetChanged(); //必须有的一步
            }
        });
        //根据 currentItem 记录的点击位置来设置"对应Item"的可见性（在list依次加载列表数据时，每加载一个时都看一下是不是需改变可见性的那一条）
        if (currentItem == position) {
            HideArea.setVisibility(View.VISIBLE);
        } else {
            HideArea.setVisibility(View.GONE);
        }
        return view;
    }

}
