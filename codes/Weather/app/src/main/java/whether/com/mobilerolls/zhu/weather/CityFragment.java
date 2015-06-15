package whether.com.mobilerolls.zhu.weather;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

import whether.com.mobilerolls.zhu.weather.dummy.DummyContent;
import whether.com.mobilerolls.zhu.weather.models.City;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class CityFragment extends ListFragment {

    private OnCityFragmentInteractionListener mListener;
    private ArrayList<String> cities;
    private String province;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cities = City.loadCities(getActivity(), province);


        // TODO: Change Adapter to display your content
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, cities));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCityFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onCityFragmentInteraction(cities.get(position));
        }
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCityFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onCityFragmentInteraction(String name);
    }

}
