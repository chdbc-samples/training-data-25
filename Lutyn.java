import java.util.Objects;

/**
 * Клас, що представляє домашню тварину "Лутинь".
 * Використовується як ключ у Map.
 */
public class Lutyn implements Comparable<Lutyn> {
    private final String nickname;
    private final double jumpLength;

    public Lutyn(String nickname, double jumpLength) {
        this.nickname = nickname;
        this.jumpLength = jumpLength;
    }

    public String getNickname() {
        return nickname;
    }

    public double getJumpLength() {
        return jumpLength;
    }

    /**
     * Реалізація порівняння для TreeMap та сортування.
     * Сортування за замовчуванням: за нікнеймом (як вимагає сортування Map за ключем).
     */
    @Override
    public int compareTo(Lutyn other) {
        // Сортування за нікнеймом (кличкою) за зростанням
        return this.nickname.compareTo(other.nickname);
    }

    // Обов'язкова реалізація для коректної роботи колекцій Set та Map
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lutyn lutyn = (Lutyn) o;
        // Ключ вважається однаковим, якщо співпадають обидва поля
        return Double.compare(lutyn.jumpLength, jumpLength) == 0 && Objects.equals(nickname, lutyn.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, jumpLength);
    }

    @Override
    public String toString() {
        // Форматування для зручного друку
        return "Lutyn(nickname='" + nickname + "', jumpLength=" + jumpLength + ")";
    }
}