package az.innakhchivan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CountryPrefix {
    AZERBAIJAN("+994", "Azerbaijan"),
    TURKEY("+90", "Turkey"),
    USA("+1", "United States"),
    RUSSIA("+7", "Russia");

    private final String prefix;
    private final String countryName;
}
