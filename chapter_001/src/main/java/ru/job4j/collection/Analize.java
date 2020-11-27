package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        HashMap<User, User> currentMap = (HashMap<User, User>) current.stream()
                .collect(Collectors.toMap(k -> k, v -> v));
        for (User prevUser : previous) {
            User currUser = currentMap.get(prevUser);
            if (currUser == null) {
                rsl.increaseDeleted();
            } else if (!prevUser.getName().equals(currUser.getName())) {
                rsl.increaseChanged();
            }
        }
        rsl.setAdded(currentMap.size() - previous.size() + rsl.getDeleted());
        return rsl;
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info() {
            this.added = 0;
            this.changed = 0;
            this.deleted = 0;
        }

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public void increaseAdded() {
            added++;
        }

        public void increaseChanged() {
            changed++;
        }

        public void increaseDeleted() {
            deleted++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed 
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
