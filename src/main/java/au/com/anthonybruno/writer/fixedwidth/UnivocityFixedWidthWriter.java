package au.com.anthonybruno.writer.fixedwidth;

import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.settings.FixedWidthSettings;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UnivocityFixedWidthWriter extends AbstractFixedWidthWriter {

    private FixedWidthWriter fixedWidthWriter;


    public UnivocityFixedWidthWriter(Writer writer, FixedWidthSettings settings) {
        super(writer, settings);
        FixedWidthFields fixedWidthFields = new FixedWidthFields();
        settings.getFixedWidthFields().forEach((field) -> {
            fixedWidthFields.addField(field.getLength());
        });
        FixedWidthWriterSettings univocitySettings = new FixedWidthWriterSettings(fixedWidthFields);
        this.fixedWidthWriter = new FixedWidthWriter(writer, univocitySettings);
    }

    @Override
    public void writeRow(List<String> row) {
        fixedWidthWriter.writeRow(row);
        fixedWidthWriter.writeValuesToRow();
    }

    @Override
    public void writeRecord(Record record) {
        List<String> values = new ArrayList<>();
        record.forEach((value) -> {
            values.add(value.toString());
        });
        writeRow(values);
    }

    @Override
    public void close() {
        fixedWidthWriter.close();
    }
}
