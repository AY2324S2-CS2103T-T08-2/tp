package seedu.address.model.order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.exceptions.InvalidDateException;


/**
 * Manages the parsing and formatting of date and time strings.
 * This class supports multiple input formats and converts them into a standard output format.
 */
public class TimeManager {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Parses a date and time string from various known formats into a standardized format.
     * If the input does not match any known format, it returns the input as is.
     *
     * @param input The date and time string to be parsed.
     * @return A date and time string formatted in the standard output format
     *         "dd MMMM yyyy, h:mm a" if the input matches any of the known formats;
     *         otherwise, returns the original input string.
     */
    public static LocalDate parseTime(String input) {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("dd MMMM yyyy"),
                DateTimeFormatter.ofPattern("dd MM yyyy")
        );

        LocalDate result = null;
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate currTime = LocalDate.parse(input, formatter);
                return currTime;
            } catch (DateTimeParseException e) {
                continue;
            }

        }
        throw new InvalidDateException();
    }

    /**
     * Formats a LocalDate into a string according to the specified format.
     *
     * @param date The LocalDate object to be formatted.
     * @return The formatted date string.
     */
    public static String formatter(LocalDate date) {
        return date.format(FORMATTER);
    }
}
