import java.util.*;

public class IMRepository<T> {
    private final Map<Integer, T> storage = new HashMap<>();
    private int currentId=1;

    public int create(T entity) {
        storage.put(currentId, entity);
        return currentId++;
    }

    public T read(int id) {
        return storage.get(id);
    }

    public void update(int id, T entity) {
        if (storage.containsKey(id)) {
            storage.put(id, entity);
        } else {
            throw new NoSuchElementException("ID does not exist in the repository.");
        }
    }

    public void delete(int id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
        } else {
            throw new NoSuchElementException("ID does not exist in the repository.");
        }
    }

    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    public T readMedicine(String name) {
        return storage.values().stream()
                .filter(entity -> entity instanceof Medicine && ((Medicine) entity).getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void updateMedicine(String name, T updatedEntity) {
        for (Map.Entry<Integer, T> entry : storage.entrySet()) {
            if (entry.getValue() instanceof Medicine && ((Medicine) entry.getValue()).getName().equals(name)) {
                storage.put(entry.getKey(), updatedEntity);
                return;
            }
        }
        throw new NoSuchElementException("Medicine with the given name does not exist in the repository.");
    }

    public void deleteMedicine(String name) {
        Integer keyToDelete = null;
        for (Map.Entry<Integer, T> entry : storage.entrySet()) {
            if (entry.getValue() instanceof Medicine && ((Medicine) entry.getValue()).getName().equals(name)) {
                keyToDelete = entry.getKey();
                break;
            }
        }
        if (keyToDelete != null) {
            storage.remove(keyToDelete);
        } else {
            throw new NoSuchElementException("Medicine with the given name does not exist in the repository.");
        }
    }
}
