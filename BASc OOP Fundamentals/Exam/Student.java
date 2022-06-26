package Exam;

import java.util.Objects;

/**
 *
 * @author James
 */

    public class Student {
        private String name;
        private int id;
        private double gpa;

        public Student()
        {
            this.name = "";
            this.id = 0;
            this.gpa = 0.00;
        }
        public Student (String name, int id, double gpa)
        {
            this.name = name;
            this.id = id;
            this.gpa = gpa;
        }
        
        public String getName()
        {
            return name;
        }
        public void setName(String name)
        {
            this.name = name;
        }
        
        public int getId()
        {
            return id;
        }
        public void setId(int id)
        {
            this.id = id;
        }
        
        public double getGpa()
        {
            return gpa;
        }
        public void setGpa(double gpa)
        {
            this.gpa = gpa;
        }
        
        public String toString()
        {
            return ( "" + name + " " + id + " " + gpa );
        }
        public boolean equals(Student student) 
        {
            return Objects.equals(name, student.name)
            && Objects.equals(id, student.id) 
                    && Objects.equals(gpa, student.gpa);
        }
}