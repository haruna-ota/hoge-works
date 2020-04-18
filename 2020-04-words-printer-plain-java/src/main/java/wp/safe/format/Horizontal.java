package wp.safe.format;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import wp.safe.core.Line;
import wp.safe.core.Words;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Horizontal implements Formatter {
    @Override
    public Line apply(Words words) {
        return new Line(
                String.join(" ", words.raw())
        );
    }
}
