package jekirdek.com.t3mobil.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jekirdek.com.t3mobil.activityes.BilgilerFragment;
import jekirdek.com.t3mobil.activityes.DersKatilimFragment;
import jekirdek.com.t3mobil.activityes.YoklamaAyarFragment;

/**
 * Created by cem
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new YoklamaAyarFragment();
        } else if (position == 1) {
            return new DersKatilimFragment();
        } else return new BilgilerFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
