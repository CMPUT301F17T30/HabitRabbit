/*
 *     <HabitRabbit- A habit tracking app.>
 *     Copyright (C) <2017>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cmput301f17t30.habitrabbit;

import com.searchly.jestdroid.JestDroidClient;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Update;

/**
 * Controller to deal with elastic search online behavior.
 */

public class ElasticSearchController {
    private static JestDroidClient client;


    public class AddEventTask extends AsyncTask<HabitEvent, Void, Void> {

        @Override
        protected Void doInBackground(HabitEvent...habitEvents) {
            verifySettings();

            for (HabitEvent habitEvent : habitEvents) {
                Index index = new Index.Builder(habitEvent).index("Team30_HabitRabbit").type("HabitEvent").build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded()){
                        habitEvent.setId(result.getId());
                        Log.i("Success","Adding Habit event success");
                    }
                    else{
                        Log.i("Error","Elasticsearch was not able to add the Habit event");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the Habit event");
                }

            }
            return null;
        }
    }

    // TODO we need a function which gets tweets from elastic search
    public static class UpdateHabitEvent extends AsyncTask<HabitEvent, Void, Void> {
        @Override
        protected Void doInBackground(HabitEvent...habitEvents) {
            verifySettings();

            // TODO Build the query

            for (HabitEvent habitEvent : habitEvents) {
                Update update = new Update.Builder(habitEvent).index("Team30_HabitRabbit").type("HabitEvent").id(habitEvent.getId()).build();

                try {
                    DocumentResult result = client.execute(update);
                    if (result.isSucceeded()) {

                    } else {
                        Log.d("Error", "The search query failed");
                    }
                    // TODO get the results of the query
                } catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }
            }

            return null;
        }

    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
