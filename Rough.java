public static String instagram(String user) {
      
            String url = url("https://instagram.com/" + username);
            String first = delete_to(url, "{\"ProfilePage\": [");
            String last = deleteAfter(first, ", {\"__typename\":");
            String json = last+"]}}}";
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(json);

                JSONObject jsonObject = (JSONObject) obj;
                JSONObject user = (JSONObject) jsonObject.get("user");
                JSONObject following = (JSONObject) user.get("follows");
                JSONObject followers = (JSONObject) user.get("followed_by");

                Boolean isprivate = (Boolean) user.get("is_private");
                Long followingcount = (Long) following.get("count");
                String biography = (String) user.get("biography");
                Boolean verified = (Boolean) user.get("is_verified");
                String name = (String) user.get("full_name");
                String profilepic = (String) user.get("profile_pic_url_hd");
                Long followerscount = (Long) followers.get("count");
                String trimlikes = String.valueOf(user);
                String trimLikesBefore = delete_to(trimlikes, "\"likes\":");
                String trimLikesAfter = deleteAfter(trimLikesBefore, "}]}");

                Object likesObj = parser.parse(trimLikesAfter);
                JSONObject likesJson = (JSONObject) likesObj;

                Long likes = (Long) likesJson.get("count");

                String nodes = String.valueOf(user);
                String trimNodesBefore = delete_to(nodes, "\"nodes\":[");
                String trimNodesAfter = deleteAfter(trimNodesBefore, "]},");
                Object nodesObj = parser.parse(trimNodesAfter);
                JSONObject jsonObj = (JSONObject) nodesObj;

                JSONObject comments = (JSONObject) jsonObj.get("comments");

                String image = (String) jsonObj.get("display_src");
                Long commentscount = (Long) comments.get("count");
                String caption = (String) jsonObj.get("caption");
                Boolean video = (Boolean) jsonObj.get("is_video");

                return "Followers: " + followerscount + "\n" +
                        "Following: " + followingcount + "\n" +
                        "Bio: " + biography + "\n" +
                        "Name: " + name + "\n" +
                        "Verified: " + verified + "\n" +
                        "Private: " + isprivate + "\n" +
                        "Profile picture: " + profilepic + "\n" +
                        "---most recent post info---" + "\n" +
                        "Likes: " + likes + "\n" +
                        "Comments: " + commentscount + "\n" +
                        "Image: " + image + "\n" +
                        "Caption: " + caption + "\n" +
                        "Video: " + video;
                        
               }
}

public static String removeTillWord(String input, String word) {
        return input.substring(input.indexOf(word));
    }

    public static String replace(String input, String word, String replace) {
        return input.replace(word, replace);
    }

    public static String delete_to(String msg, String trim){
        String trimStart = removeTillWord(msg, trim);
        String trimEnd = replace(trimStart, trim, "");
        return trimEnd;
    }

    public static String deleteAfter(String msg, String trim){
        return msg.substring(0, msg.indexOf(trim));
    }
