// import java.io.Serializable;

// public class SavenLoad {
//     public static void save(String filename, Class<?> object) {
//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employee.ser"))) {
//             oos.writeObject(employee);
//             System.out.println("Employee object has been serialized.");
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public static <T> void load(String filename) {
//         T deserializedEmployee = null;
//         try (T ois = new ObjectInputStream(new FileInputStream("employee.ser"))) {
//             deserializedEmployee = (T) ois.readObject();
//             System.out.println("Employee object has been deserialized.");
//         } catch (IOException | ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//     }
// }
