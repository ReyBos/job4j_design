package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        HashSet<User> previousSet = new HashSet<>(previous);
        HashSet<User> currentSet = new HashSet<>(current);
        for (User user : current) {
            if (!previousSet.contains(user)) {
                rsl.increaseAdded();
                currentSet.remove(user);
            }
        }
        for (User user : previous) {
            if (!currentSet.contains(user)) {
                rsl.increaseDeleted();
            }
        }
        for (User currUser: currentSet) {
            int prevUserIndex = previous.indexOf(currUser);
            User prevUser = previous.get(prevUserIndex);
            if (!prevUser.getName().equals(currUser.getName())) {
                rsl.increaseChanged();
            }
        }
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
