package JavaStream.type;

public class Tuple<TFirst, TSecond> {

    public final TFirst first;
    public final TSecond second;

    private Tuple(TFirst first, TSecond second) {
        this.first = first;
        this.second = second;
    }

    public static <TFirst, TSecond> Tuple<TFirst, TSecond> create(TFirst first, TSecond second) {
        return new Tuple<>(first, second);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
