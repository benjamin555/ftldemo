
<#list list as m>
	
<TextView
                android:id="@+id/${m.id}"
                 android:layout_height="50dp"
                android:textSize="@dimen/grid_title_txt_size" 
                style="${m.style}"
                />

 <View
                android:layout_width="0.5dp"
                android:layout_height="50dp"
                android:background="@color/grid_line_bg" />
        
</#list>