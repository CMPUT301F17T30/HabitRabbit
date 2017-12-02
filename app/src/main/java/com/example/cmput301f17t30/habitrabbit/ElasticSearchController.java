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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.Update;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;

/**
 * Controller to deal with elastic search online behavior.
 */

public class ElasticSearchController {
    private static JestDroidClient client;


    public static class AddEventTask extends AsyncTask<HabitEvent, Void, Void> {

        @Override
        protected Void doInBackground(HabitEvent...habitEvents) {
            verifySettings();

            for (HabitEvent habitEvent : habitEvents) {
                Index index = new Index.Builder(habitEvent).index("team30_habitrabbit").type("HabitEvent").build();

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
                Index index = new Index.Builder(habitEvent).index("team30_habitrabbit").type("HabitEvent").id(habitEvent.getId()).build();
                try {
                    DocumentResult result = client.execute(index);
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

    public static class DeleteHabitEvent extends AsyncTask<HabitEvent, Void, Void> {
        @Override
        protected Void doInBackground(HabitEvent...habitEvents){
            verifySettings();

            for (HabitEvent habitEvent : habitEvents) {
                Delete delete = new Delete.Builder(habitEvent.getId()).index("team30_habitrabbit").type("HabitEvent").build();

                try {
                    DocumentResult result = client.execute(delete);
                    if (result.isSucceeded()) {
                        Log.i("Sucess", "Sucessfully deleted Habit Event");
                    } else {
                        Log.i("Error", "Elasticsearch was unable to delete Habit Event");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Failed to communicate with server");
                }
            }

            return null;

        }
    }

    public static class AddHabitTask extends AsyncTask<Habit, Void, Void> {

        protected Void doInBackground(Habit...habits){
            verifySettings();
            for (Habit habit : habits) {
                Index index = new Index.Builder(habit).index("team30_habitrabbit").type("Habit").build();

                try {
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded()){
                        habit.setId(result.getId());
                        Log.i("Sucess","Sucessfully added Habit");
                    }
                    else{
                        Log.i("Error","Elasticsearch was unable to add Habit");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "Failed to build and send new Habit");
                }

            }
            return null;

        }
    }

    public static class EditHabitTask extends AsyncTask<Habit, Void, Void> {
        @Override
        protected Void doInBackground(Habit...habits){
            verifySettings();

            for (Habit habit : habits) {
                Index index = new Index.Builder(habit).index("team30_habitrabbit").type("Habit").id(habit.getId()).build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.i("Sucess", "Sucessfully updated Habit");
                    } else {
                        Log.i("Error", "Elasticsearch was unable to update Habit");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Failed to build and send updated Habit");
                }
            }

            return null;

        }
    }

    public static class DeleteHabitTask extends AsyncTask<Habit, Void, Void> {
        @Override
        protected Void doInBackground(Habit...habits){
            verifySettings();

            for (Habit habit : habits) {
                Delete delete = new Delete.Builder(habit.getId()).index("team30_habitrabbit").type("Habit").build();

                try {
                    DocumentResult result = client.execute(delete);
                    if (result.isSucceeded()) {
                        Log.i("Sucess", "Sucessfully deleted Habit");
                    } else {
                        Log.i("Error", "Elasticsearch was unable to delete Habit");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Failed to communicate with server");
                }
            }

            return null;

        }
    }

    public static class GetHabitsTask extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String...user_id){
            verifySettings();

            ArrayList<Habit> habits = new ArrayList<Habit>();

            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"userId\" : \""+user_id[0]+"\" }\n" +
                    "    }\n" +
                    "}";


            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("Habit")
                    .build();
            try {
                // TODO get the results of the query
                SearchResult result = client.execute(search);
                if(result.isSucceeded()){
                    List<Habit> foundHabits = result.getSourceAsObjectList(Habit.class);
                    habits.addAll(foundHabits);
                    habitController.addAllHabits(habits);
                }
                else{
                    Log.e("Error", "The seach query failed");
                }

            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }

    public static class GetFriendList extends AsyncTask<User, Void, Void>{
        @Override
        protected Void doInBackground(User...user) {
            verifySettings();

            User user1 = user[0];
            ArrayList<String> friends = user1.getFriendsList();
            ArrayList<User> users = new ArrayList<>();

            for (int i = 0; i < friends.size() ; i++) {
                String query = "{\n" +
                        "    \"query\" : {\n" +
                        "        \"term\" : { \"user_id\" : \"" + friends.get(i) + "\" }\n" +
                        "    }\n" +
                        "}";


                Search search = new Search.Builder(query)
                        .addIndex("team30_habitrabbit")
                        .addType("User")
                        .build();
                try {
                    // TODO get the results of the query
                    SearchResult result = client.execute(search);
                    if (result.isSucceeded()) {
                        User foundFriend = result.getSourceAsObject(User.class);
                        users.add(0, foundFriend);
                    } else {
                        Log.e("Error", "The seach query failed");
                    }

                } catch (Exception e) {
                    Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }
            }
            friendController.setFriendsList(users);
            return null;
        }


    }

    public static class AddUserTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User...users) {
            verifySettings();

            for (User user : users) {
                Index index = new Index.Builder(user).index("team30_habitrabbit").type("User").build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded()){
                        user.setJestId(result.getId());
                        Log.i("Success","Adding User success");
                    }
                    else{
                        Log.i("Error","Elasticsearch was not able to add the User");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the User");
                }

            }
            return null;
        }
    }

    public static class UpdateUser extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User...users) {
            verifySettings();

            // TODO Build the query

            for (User user : users) {
                Update update = new Update.Builder(user).index("team30_habitrabbit").type("User").id(user.getJestId()).build();

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
