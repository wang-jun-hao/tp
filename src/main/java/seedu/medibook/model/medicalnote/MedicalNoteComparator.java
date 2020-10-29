package seedu.medibook.model.medicalnote;

import java.util.Comparator;

import seedu.medibook.model.commonfields.Date;

/**
 * A comparator of medical notes that works with sort to give a list of medical notes from most recent to least recent.
 */
public class MedicalNoteComparator implements Comparator<MedicalNote> {
    @Override
    public int compare(MedicalNote o1, MedicalNote o2) {
        Date date1 = o1.date;
        Date date2 = o2.date;
        return -date1.compareTo(date2);
    }
}
