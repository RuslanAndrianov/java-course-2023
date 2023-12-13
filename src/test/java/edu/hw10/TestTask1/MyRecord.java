package edu.hw10.TestTask1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;

public record MyRecord(@Min(20) byte byteField, @Max(100) double doubleField, @NotNull @Min(5) @Max(7) String stringField) { }
