package com.aa.bdf.studentregistration.courseapi.util;

import com.aa.bdf.studentregistration.courseapi.entity.Course;
import com.aa.bdf.studentregistration.studentapi.entity.Student;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

public class PDFGenerator {

    private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

    public static ByteArrayInputStream generateCoursesPDFReport(List<Course> courseList) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 24, BaseColor.BLUE);
            Paragraph para = new Paragraph( "Courses Schedule", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100.0f);
            table.setWidths(new float[] {1.0f, 2.0f, 3.0f, 2.0f, 3.0f});
            table.setSpacingBefore(10);
            // Add PDF Table Header ->
            Stream.of("ID", "Course Name", "Description", "Start Date", "Students")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.CYAN);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (Course course : courseList) {
                PdfPCell idCell = new PdfPCell(new Phrase(course.getId().toString()));
//                idCell.setPaddingLeft(2);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell nameCell = new PdfPCell(new Phrase(course.getCourseName()));
//                nameCell.setPaddingLeft(4);
                nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(nameCell);

                PdfPCell descriptionCell = new PdfPCell(new Phrase(course.getDescription()));
                descriptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                descriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                descriptionCell.setPaddingRight(4);
                table.addCell(descriptionCell);

                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                PdfPCell startDateCell = new PdfPCell(new Phrase(simpleDateFormat.format(course.getStartDate())));
                startDateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                startDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                startDateCell.setPaddingRight(4);
                table.addCell(startDateCell);

                PdfPCell studentsCell = new PdfPCell(new Phrase(convertUsingStringBuilder(course.getStudents())));
                studentsCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                studentsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                studentsCell.setPadding(4);
                table.addCell(studentsCell);
            }
            document.add(table);

            document.close();
        }catch(DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public static String convertUsingStringBuilder(List<Student> studentList)
    {
        StringBuilder namesStr = new StringBuilder();
        for(Student st : studentList)
        {
//            namesStr = namesStr.length() > 0 ? namesStr.append(",").append(st.getFirstName()+" "+st.getLastName()) : namesStr.append(st.getFirstName()+" "+st.getLastName());
            namesStr=namesStr.append(" - "+st.getFirstName()+" "+st.getLastName()+"\n");
        }
        return namesStr.toString();
    }
}
