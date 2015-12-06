
<#list list as m>
	
<TextView
                android:id="@+id/${m.id}"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="@dimen/grid_title_txt_size" />

            <View
                android:layout_width="0.5dp"
                android:layout_height="match_parent"
                android:background="#FF909090" />
        
</#list>