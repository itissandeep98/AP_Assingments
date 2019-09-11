package Ap_assignment.Lab1;
import java.util.*;
class Company{
    private String name, Course[], status;
    private int required_students;
    private int[] scores;
    Company(){
        this.name="NOT ASSIGNED";
        this.required_students=0;
        this.status="OPEN";
    }
    Company(String name, int required_students, String course[],int n){
        this.name=name;
        this.required_students=required_students;
        this.Course=course;
        this.status="OPEN";
        scores=new int[n+1];
    }
    public void display(){
        System.out.println("--------------------------------");
        System.out.println(this.name);
        System.out.println("Course criteria");
        for (String i : Course){
            System.out.println(i);
        }
        System.out.println("Number of required students = "+ this.required_students);
        System.out.println(this.status);
        System.out.println("--------------------------------");
    }
    public boolean iseligible(Student s){
        for(String i: this.Course){
            if(i.equals(s.getCourse())){
                return true;
            }
        }
        return false;
    }
    public void setScore(int index ,int value){
        this.scores[index]=value;
    }
    public int getScore(int index){
        return this.scores[index];
    }
    public String getName() {
	    return this.name;
    }
    public String getStatus() {
    	return this.status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    public int getRequired_students() {
    	return this.required_students;
    }
    public void setRequired_students(int val) {
    	this.required_students=val;
    }
}
class Student{
    static int curr_roll=1;
    private String  course, status, company;
    private int roll;
    private float cgpa;
    Student(){
        this.status="NOT PLACED";
        this.company="None";
        this.course="NOT ASSIGNED";
    }
    Student(float cgpa,String course){
        this.roll=curr_roll;
        this.cgpa=cgpa;
        this.course=course;
        curr_roll+=1;
        this.status="NOT PLACED";
    }
    public void display(){
        System.out.println("--------------------------------");
        System.out.println(this.getRoll());
        System.out.println(this.cgpa);
        System.out.println(this.course);
        if(company!=null){
            System.out.println(this.company);
        }
        else{
            System.out.println("Placement Status: Not placed");
        }
        System.out.println("--------------------------------");
    }


    public String getCourse() {
    	return this.course;
    }
    public String getStatus() {
    	return this.status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    public String getCompany() {
    	return this.company;
    }
    public void setCompany(String company) {
    	this.company = company;
    }
    public int getRoll() {
    	return this.roll;
    }
    public float getCgpa() {
    	return this.cgpa;
    }
}
public class Assignment1 {
    static List<Company> companies=new ArrayList<Company>();
    static List<Student> students=new ArrayList<Student>();
    private static final Scanner scan=new Scanner(System.in);
    static Company comp;
    public static void main(String[] args) {
        int query,i,roll,n;
        String c_name;
        boolean flag;

        n=scan.nextInt();
        for (i = 0; i < n; i++) {
            float cgpa=scan.nextFloat();
            String course=scan.next();
            students.add(new Student(cgpa,course));
        }
        while(true){
            query=scan.nextInt();
            switch (query) {
                case 1:
                    String name=scan.next();
                    System.out.print("Number of Eligible Courses = ");
                    int num=scan.nextInt();
                    String course[]=new String[num];
                    for(i=0;i<num;i++){
                        course[i]=scan.next();
                    }
                    System.out.print("Number of Required students = ");
                    int num1=scan.nextInt();
                    Company c=new Company(name,num1,course,n);
                    c.display();
                    companies.add(c);
                    if(num>0){
                        System.out.println("Enter scores for the technical round");
                        for(Student s: students){
                            if(c.iseligible(s)){
                                System.out.println("Enter score for Roll no. "+s.getRoll());
                                c.setScore(s.getRoll(), scan.nextInt());
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Accounts removed for: ");
                    i=0;
                    while(i<students.size()){
                        if(students.get(i).getStatus().equals("PLACED")){
                            System.out.println(students.get(i).getRoll());
                            students.remove(i);
                            i-=1;
                        }
                        i+=1;
                    }
                    break;
                case 3:
                    flag=true;
                    System.out.println("Accounts removed for: ");
                    i=0;
                    while(i<companies.size()){
                        if(companies.get(i).getStatus().equals("CLOSED")){
                            flag=false;
                            System.out.println(companies.get(i).getName());
                            companies.remove(i);
                            i-=1;
                        }
                        i+=1;
                    }
                    if(flag){
                        System.out.println("No Account found that can be removed");
                    }
                    break;
                case 4:
                    int count=0;
                    i=0;
                    while(i<students.size()){
                        if(students.get(i).getStatus().equals("NOT PLACED")){
                            count++;
                        }
                        i+=1;
                    }
                    System.out.println(count+" Students left");
                    break;
                case 5:
                    i=0;
                    while(i<companies.size()){
                        if(companies.get(i).getStatus().equals("OPEN")){
                            System.out.println(companies.get(i).getName());
                        }
                        i+=1;
                    }
                    break;
                case 6:
                    c_name=scan.next();
                    System.out.println("Roll number of selected students: ");
                    i=0;
                    comp=companies.get(0);
                    while(i<companies.size()){
                        if(companies.get(i).getName().equals(c_name)){
                            comp=companies.get(i);
                            break;
                        }
                        i+=1;
                    }
                    if(comp.getRequired_students()>students.size()){
                        while(students.size()>0){
                            System.out.println(students.get(0).getRoll());
                            students.remove(0);
                        }
                        comp.setStatus("CLOSE");
                    }
                    else{
                        List<Student> temp=new ArrayList<Student>();
                        for(Student s: students){
                            if(s.getStatus().equals("NOT PLACED") && comp.iseligible(s)){
                                temp.add(s);
                            }
                        }
                        // Collections.sort(temp,Comparator.comparing(Student::getCgpa));
                        // Collections.sort(temp,(o1,o2) -> (int)(o1.getCgpa() - o2.getCgpa()));
                        Collections.sort(temp,(o1,o2) -> comp.getScore(o1.getRoll())-comp.getScore(o2.getRoll()));
                        while(temp.size()>0 && comp.getRequired_students()>0){
                            System.out.println(temp.get(0).getRoll());
                            temp.get(0).setStatus("PLACED");
                            temp.get(0).setCompany(comp.getName());
                            temp.remove(0);
                            comp.setRequired_students(comp.getRequired_students()-1);
                        }
                        if(comp.getRequired_students()==0){
                            comp.setStatus("CLOSED");
                        }
                    }
                    break;
                case 7:
                    flag=true;
                    c_name=scan.next();
                    i=0;
                    while(i<companies.size()){
                        if(companies.get(i).getName().equals(c_name)){
                            companies.get(i).display();
                            flag=false;
                            break;
                        }
                        i+=1;
                    }
                    if(flag){
                        System.out.println("NO company found with that name");
                    }
                    break;
                case 8:
                    flag=true;
                    roll=scan.nextInt();
                    i=0;
                    while(i<students.size()){
                        if(students.get(i).getRoll()==roll){
                            students.get(i).display();
                            flag=false;
                            break;
                        }
                        i+=1;
                    }
                    if(flag){
                        System.out.println("No student with given roll number has an account");
                    }
                    break;
                case 9:
                    flag=true;
                    roll=scan.nextInt();
                    for(Student s: students){
                        if(s.getRoll()==roll && s.getStatus().equals("NOT PLACED")){
                            flag=false;
                            for(Company com: companies){
                                if(com.iseligible(s)){
                                    System.out.println(com.getName()+" "+com.getScore(roll));
                                }
                            }
                            break;
                        }
                    }
                    if(flag){
                        System.out.println("No student with given roll number has an account");
                    }
                    break;
                default:
                    System.out.println("!!!!!!!!!! Exiting !!!!!!!");
                    System.exit(0);
                    break;
            }
        }
    }
}
