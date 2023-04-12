package dev.webfx.cli.spoon.case1;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;

/**
 * @author Bruno Salmon
 */
public class SpoonCase1 {

    public static void main(String... args) {
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setComplianceLevel(11);
        launcher.addInputResource("../webfx-cli-cases/webfx-cli-case1/src/main/java");
        CtModel model = launcher.buildModel();

        // Find all the packages used in the code
        for (CtElement ctClass : model.getElements(e -> e instanceof CtClass || e instanceof CtInterface)) {
            CtType<?> ctType = (CtType<?>) ctClass;
            System.out.println(ctType.getQualifiedName() + " is using:");
            for (CtTypeReference<?> type : ctType.getUsedTypes(true)) {
                System.out.println(" - " + type.getQualifiedName() + " (package " + type.getPackage() + ")");
            }
        }
    }

}
