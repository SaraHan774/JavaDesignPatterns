package composite;

import java.util.ArrayList;
import java.util.List;

interface ITeacher{
    public String getDetails();
}

class Teacher implements ITeacher{

    private String teacherName;
    private String deptName;
    private List<ITeacher> controls;

    Teacher(String teacherName, String deptName){
        this.teacherName = teacherName;
        this.deptName = deptName;
        controls = new ArrayList<>();
    }

    public void Add(Teacher teacher){
        controls.add(teacher);
    }

    public void Remove(Teacher teacher){
        controls.remove(teacher);
    }

    public List<ITeacher> getControllingDepts(){
        return controls;
    }

    @Override
    public String getDetails() {
        return (teacherName + " is the " + deptName);
    }
}

public class CompositePatternEx {

    public static void main(String[] args) {
        Teacher principal = new Teacher("Dr.Som", "Principal");
        Teacher hodMaths = new Teacher("Mrs.Das", "Hod-Math");
        Teacher hodCompSc = new Teacher("Mr.Sarcar", "Hod-ComputerSc");

        Teacher mathTeacher1 = new Teacher("Math Teacher 1", "MathsTeacher");
        Teacher mathTeacher2 = new Teacher("Math Teacher 2", "MathsTeacher");

        Teacher cseTeacher1 = new Teacher("CSE Teacher-1", "CSETeacher");
        Teacher cseTeacher2 = new Teacher("CSE Teacher-2", "CSETeacher");
        Teacher cseTeacher3 = new Teacher("CSE Teacher-3", "CSETeacher");

        //교장의 대학 조직의 가장 위에 있다.
        principal.Add(hodCompSc);
        principal.Add(hodMaths);

        //수학 선생님들은 수학과장 밑에 있다.
        hodMaths.Add(mathTeacher1);
        hodMaths.Add(mathTeacher2);

        //컴퓨터 과학 선생님들은 학과장 밑에 있다.
        hodCompSc.Add(cseTeacher1);
        hodCompSc.Add(cseTeacher2);
        hodCompSc.Add(cseTeacher3);

        //리프 노드. 수학 선생님들 밑에는 아무도 없음.
        mathTeacher1.Add(null);
        mathTeacher2.Add(null);

        cseTeacher1.Add(null);
        cseTeacher2.Add(null);
        cseTeacher3.Add(null);

        System.out.println("*** Composite Pattern Demo ***");
        System.out.println("\n The college has following structure \n");
        System.out.println(principal.getDetails());
        List<ITeacher> hods = principal.getControllingDepts();

        for (int i = 0; i < hods.size(); i++) {
            System.out.println("\t " + hods.get(i).getDetails());
        }

        List<ITeacher> mathTeachers = hodMaths.getControllingDepts();
        for (int i = 0; i < mathTeachers.size(); i++) {
            System.out.println("\t\t" + mathTeachers.get(i).getDetails());
        }

        List<ITeacher> cseTeachers = hodCompSc.getControllingDepts();
        for (int i = 0; i < cseTeachers.size(); i++) {
            System.out.println("\t\t" + cseTeachers.get(i).getDetails());
        }

        //one computer teacher is leaving
        hodCompSc.Remove(cseTeacher2);
        System.out.println("\n After CSE Teacher-2 leaving the organization - CSE has following employees : ");
        cseTeachers = hodCompSc.getControllingDepts();
        for (int i = 0; i < cseTeachers.size(); i++) {
            System.out.println("\t\t" + cseTeachers.get(i).getDetails());
        }

    }
}
