package stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author 张卓
 * @Description
 * @Date 创建于 2020/8/14 11:35
 */
public class StreamTest {


    public static void main(String[] args) {

        List<ProjectConfVersion> list = new ArrayList<ProjectConfVersion>();
        ProjectConfVersion projectConfVersion = new ProjectConfVersion();
        projectConfVersion.setProject("小米10");
        projectConfVersion.setConfiguration("4G+64G");

        list.add(projectConfVersion);

        projectConfVersion = new ProjectConfVersion();
        projectConfVersion.setProject("小米10");
        projectConfVersion.setConfiguration("6G+128G");
        list.add(projectConfVersion);

//        list.stream().flatMap()

        HashMap<String, List<String>> collect =
                list.stream().collect(
                        Collectors.groupingBy(ProjectConfVersion::getProject, HashMap::new,
                                Collectors.mapping(ProjectConfVersion::getConfiguration, Collectors.toList())));
        System.out.println(collect);
//        Map<String, String> map = list.stream().collect(Collectors.toMap(ProjectConfVersion::getProject, ProjectConfVersion::getConfiguration));
//        System.out.println(map);

    }

    static class ProjectConfVersion {
        private String project;
        private String configuration;
        private String version;

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getConfiguration() {
            return configuration;
        }

        public void setConfiguration(String configuration) {
            this.configuration = configuration;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
