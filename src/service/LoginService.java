@Service
public class LoginService {
    public boolean login(String name) {
        return name != null && !name.isEmpty();
    }
}
