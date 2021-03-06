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

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import static com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.filterDone;
import static com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.fromHistory;
import static com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.resetHistory;
import static com.example.cmput301f17t30.habitrabbit.LoginActivity.elasticDoneL;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventLoad;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.elasticDone;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendLoad;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendRequests;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendsList;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.fromMain;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.userLoad;

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


    public static class GetEventTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... user_id) {
            verifySettings();

            ArrayList<HabitEvent> events = new ArrayList<>();

            String query = "{\n" +
            "    \"query\": {\n" +
            "    \"bool\": {\n" +
            "        \"must\": [\n" +
            "        {\n" +
            "            \"term\": {\n" +
            "            \"userId\": \"" + user_id[0] + "\"\n" +
            "        }\n" +
            "        },\n" +
            "        {\n" +
            "            \"range\": {\n" +
            "            \"date\": {\n" +
            "                \"gte\": \"now-4w\",\n" +
            "                \"lte\": \"now\"\n" +
            "            }\n" +
            "        }\n" +
            "        }\n" +
            "       ]\n" +
            "    }\n" +
            "},\n" +
            "    \"sort\": { \"date\" : \"desc\" }\n" +
            "}\n";


            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("HabitEvent")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<HabitEvent> foundEvents = result.getSourceAsObjectList(HabitEvent.class);
                    events.addAll(foundEvents);
                    Log.d("elastic: ", ""+events);
                    eventList.setEventList(events);
                } else {
                    Log.e("Error", "The seach query failed");
                }

            } catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (fromHistory != null){
                resetHistory.setDone(true);
            }
            if (eventLoad != null){
                eventLoad.setDone(true);
            }

        }
    }

    public static class GetFilteredEventsTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... habitID) {

            ArrayList<HabitEvent> events = new ArrayList<>();

            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"habitType.id\" : \"" + habitID[0].toLowerCase() + "\" }\n" +
                    "    }\n" +
                    "}";

            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("HabitEvent")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<HabitEvent> foundEvents = result.getSourceAsObjectList(HabitEvent.class);
                    events.addAll(foundEvents);
                    eventList.setEventList(events);
                } else {
                    Log.e("Error", "The seach query failed");
                }

            } catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return null;

        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            filterDone.setDone(true);
        }


    }

    public static class GetSearchedEventsTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... user_id) {
            verifySettings();

            ArrayList<HabitEvent> events = new ArrayList<>();

            String query = "{\n" +
                    "    \"query\": {\n" +
                    "       \"bool\": {\n" +
                    "           \"must\": [\n" +
                    "               {\n" +
                    "                   \"term\": {\n" +
                    "                       \"userId\": \"" + userController.getUsername() + "\"\n" +
                    "                   }\n" +
                    "               },\n" +
                    "               {\n" +
                    "                   \"match\": {\n" +
                    "                       \"Comment\": \"" + user_id[0].toLowerCase() + "\"\n" +
                    "                   }\n" +
                    "               }\n" +
                    "           ]\n" +
                    "       }\n" +
                    "   },\n" +
                    "    \"sort\": { \"date\" : \"desc\" }\n" +
                    "}\n";


            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("HabitEvent")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<HabitEvent> foundEvents = result.getSourceAsObjectList(HabitEvent.class);
                    events.addAll(foundEvents);
                    eventList.setEventList(events);
                } else {
                    Log.e("Error", "The seach query failed");
                }

            } catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            filterDone.setDone(true);
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

            ArrayList<Habit> habits = new ArrayList<>();

            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"userID\" : \""+user_id[0]+"\" }\n" +
                    "    }\n" +
                    "}";


            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("Habit")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded()){
                    List<Habit> foundHabits = result.getSourceAsObjectList(Habit.class);
                    habits.addAll(foundHabits);
                    habitList.addAll(habits);
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
            elasticDone.setDone(true);
        }
    }

    public static class GetFriendTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String...friend) {
            verifySettings();
            // TODO Build the query
            for (String friend_id: userController.getFriends()) {
                String query = "{\n" +
                        "    \"query\" : {\n" +
                        "        \"term\" : { \"userId\" : \"" + friend_id + "\" }\n" +
                        "    }\n" +
                        "}";


                Search search = new Search.Builder(query)
                        .addIndex("team30_habitrabbit")
                        .addType("User")
                        .build();

                try {
                    SearchResult result = client.execute(search);
                    User user = result.getSourceAsObject(User.class);
                    if (user != null) {
                        Friend foundFriend = new Friend(user);
                        friendsList.addFriend(foundFriend);
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

        @Override
        protected void onPostExecute(Void aVoid) {
            friendLoad.setDone(true);
        }


    }

   public static class GetFriendEventsTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String...nothing) {
            verifySettings();

            for (Friend friend: friendsList.getFriends()) {
                ArrayList<HabitEvent> friendEvents = new ArrayList<>();
                ArrayList<Habit> friendHabits = new ArrayList<>();
                String friendId = friend.getUser().getUserId();
                String query = "{\n" +
                        "    \"query\" : {\n" +
                        "        \"term\" : { \"userID\" : \"" + friendId + "\" }\n" +
                        "    }\n" +
                        "}";


                Search search = new Search.Builder(query)
                        .addIndex("team30_habitrabbit")
                        .addType("Habit")
                        .build();
                try {
                    // TODO get the results of the query
                    SearchResult result = client.execute(search);
                    if (result.isSucceeded()) {
                        List<Habit> habits = result.getSourceAsObjectList(Habit.class);
                        friendHabits.addAll(habits);
                        for (Habit friendHabit : friendHabits) {
                            friendHabit.getTitle();
                            String queryEvent = "{\n" +
                                    "    \"query\" : {\n" +
                                    "       \"term\" : {\"habitType.id\" : \"" + friendHabit.getId().toLowerCase() + "\"}\n" +
                                    "    },\n" +
                                    "    \"sort\": { \"date\" : \"desc\" },\n" +
                                    "    \"size\": 1\n" +
                                    "}";
                            Search searchEvent = new Search.Builder(queryEvent)
                                    .addIndex("team30_habitrabbit")
                                    .addType("HabitEvent")
                                    .build();
                            try {
                                SearchResult resultEvent = client.execute(searchEvent);
                                HabitEvent friendEvent = resultEvent.getSourceAsObject(HabitEvent.class);
                                if(friendEvent != null){
                                    friendEvents.add(friendEvent);
                                }
                                else{
                                    Log.e("Error", "the event is null");
                                }

                            }
                            catch (Exception e) {
                                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                            }

                        }
                        friend.setRecentEvents(friendEvents);
                    } else {
                        Log.e("Error", "The seach query failed");
                    }

                } catch (Exception e) {
                    Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }
            }
            return null;
        }


    }



    public static class GetFriendRequestTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String...user_id) {
            verifySettings();

            ArrayList<FriendRequest> requests = new ArrayList<>();

            // TODO Build the query
            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "                   \"term\" : { \"reciever\" : \"" + user_id[0] +"\" }\n" +
                    "    }\n" +
                    "}";


            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("FriendRequest")
                    .build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<FriendRequest> request = result.getSourceAsObjectList(FriendRequest.class);
                    requests.addAll(request);
                    friendRequests.setRequestsList(requests);
                } else {
                    Log.d("Error", "The search for request query failed");
                }
                // TODO get the results of the query
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return null;
        }


    }

    public static class AddFriendRequestTask extends AsyncTask<FriendRequest, Void, Void> {

        @Override
        protected Void doInBackground(FriendRequest...friendRequests) {
            verifySettings();

            for (FriendRequest request : friendRequests) {
                Index index = new Index.Builder(request).index("team30_habitrabbit").type("FriendRequest").build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded()){
                        request.setId(result.getId());
                        Log.i("Success","Send Request success");
                    }
                    else{
                        Log.i("Error","Elasticsearch was not able to send the Request");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the Request");
                }

            }
            return null;
        }
    }


    public static class DeleteRequestTask extends AsyncTask<FriendRequest, Void, Void> {
        @Override
        protected Void doInBackground(FriendRequest...friendRequests){
            verifySettings();

            for (FriendRequest request : friendRequests) {
                Delete delete = new Delete.Builder(request.getId()).index("team30_habitrabbit").type("FriendRequest").build();

                try {
                    DocumentResult result = client.execute(delete);
                    if (result.isSucceeded()) {
                        Log.i("Sucess", "Sucessfully deleted Request");
                    } else {
                        Log.i("Error", "Elasticsearch was unable to delete Request");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Failed to communicate with server");
                }
            }

            return null;

        }
    }

    public static class AcceptRequestTask extends AsyncTask<FriendRequest, Void, Void> {
        @Override
        protected Void doInBackground(FriendRequest...friendRequests){
            verifySettings();

            for (FriendRequest request : friendRequests) {
                String user = request.getSender();
                User sender = new User();
                String query = "{\n" +
                        "    \"query\" : {\n" +
                        "        \"term\" : { \"userId\" : \""+user+"\" }\n" +
                        "    }\n" +
                        "}";


                Search search = new Search.Builder(query)
                        .addIndex("team30_habitrabbit")
                        .addType("User")
                        .build();

                try {
                    SearchResult addresult = client.execute(search);
                    sender = addresult.getSourceAsObject(User.class);
                    if (sender != null) {
                        sender.addFriend(request.getReciever());
                        Index index = new Index.Builder(sender).index("team30_habitrabbit").type("User").id(sender.getJestId()).build();

                        try {
                            DocumentResult result = client.execute(index);
                            if (result.isSucceeded()) {
                                Log.i("Sucess", "Sucessfully added Friend");
                            } else {
                                Log.i("Error", "Elasticsearch was unable to add Friend");
                            }
                        } catch (Exception e) {
                            Log.i("Error", "Failed to build and send added Friend");
                        }
                    }
                    // TODO get the results of the query
                } catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }



            }

            return null;

        }
    }


    public static class GetSenderTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String...user_id) {
            verifySettings();

            // TODO Build the query
            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"userId\" : \""+user_id[0]+"\" }\n" +
                    "    }\n" +
                    "}";


            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("User")
                    .build();

            try {
                SearchResult result = client.execute(search);
                User user = result.getSourceAsObject(User.class);
                if (user != null) {
                    userController.setUser(user);
                } else {
                    Log.d("Error", "The search query failed");
                }
                // TODO get the results of the query
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
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
                        userController.setUser(user);
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

    public static class UpdateUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User...users) {
            verifySettings();

            // TODO Build the query

            for (User user : users) {
                Index index = new Index.Builder(user).index("team30_habitrabbit").type("User").id(user.getJestId()).build();

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

    public static class GetUserTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String...user_id) {
            verifySettings();

            // TODO Build the query
            String query = "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"userId\" : \""+user_id[0]+"\" }\n" +
                    "    }\n" +
                    "}";


            Search search = new Search.Builder(query)
                    .addIndex("team30_habitrabbit")
                    .addType("User")
                    .build();

                try {
                    SearchResult result = client.execute(search);
                    User user = result.getSourceAsObject(User.class);
                    if (user != null) {
                        userController.setUser(user);
                        userController.setUserExist(Boolean.TRUE);
                    } else {
                        Log.d("Error", "The search query failed");
                        userController.setUserExist(Boolean.FALSE);
                    }
                    // TODO get the results of the query
                } catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (fromMain == Boolean.FALSE) {
                elasticDoneL.setDone(true);
            }
            else {
                userLoad.setDone(true);
            }
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


