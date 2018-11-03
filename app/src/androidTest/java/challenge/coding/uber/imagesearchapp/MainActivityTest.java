package challenge.coding.uber.imagesearchapp;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import challenge.coding.uber.imagesearchapp.ui.MainActivity;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule  = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void isPhotoListPresent(){
        MainActivity activity = rule.getActivity();
        View photoList = activity.findViewById( R.id.photos_lv);
        assertThat(photoList,notNullValue());
        assertThat(photoList, instanceOf(RecyclerView.class));
        RecyclerView listView = (RecyclerView) photoList;
        RecyclerView.Adapter adapter = listView.getAdapter();
        assertThat(adapter, instanceOf( RecyclerView.Adapter.class));
    }
}
