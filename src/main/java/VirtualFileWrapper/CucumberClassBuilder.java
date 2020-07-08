package VirtualFileWrapper;

import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.joor.Reflect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CucumberClassBuilder {
    private final StringBuilder content = new StringBuilder();
    private String packageName;
    private String className;
    private String extendsClass;
    private final Set<String> interfaces = new HashSet<>();
    private Map<String, String> cucumberOptions = new HashMap<>();

    private final Set<String> imports = new HashSet<>();


    @SneakyThrows
    public Class build() {
        buildSystem();
        buildImports();
        buildCucumberOptions();
        buildBody();
        Reflect.compile(String.format("%s.%s", packageName, className), content.toString());
        return Class.forName(packageName+"."+className);
    }

    private void buildBody() {
        content.append("\n public class ").append(className);
        if (extendsClass != null && !extendsClass.isEmpty()) {
            content.append(" extends ").append(extendsClass);
        }
        if (!interfaces.isEmpty()){
            content.append(" implements");
            interfaces.forEach(o -> content.append(o)
                    .append(", "));
            content.deleteCharAt(content.lastIndexOf(","));
        }
        content.append("{}");
    }

    private void buildCucumberOptions() {
        content.append('\n');
        content.append("@CucumberOptions(");
        cucumberOptions.forEach((key, value) -> content.append(key)
                .append('=')
                .append(value)
                .append(','));
        content.deleteCharAt(content.lastIndexOf(","));
        content.append(')');
    }

    private void buildImports() {
        content.append('\n');
        imports.forEach(o -> content.append("import ").append(o).append(";\n"));
    }

    private void buildSystem() {
        content.append("package ").append(packageName).append(';');
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withPackageName(@NotNull String packageName){
        this.packageName=packageName;
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withClassName(@NotNull String className){
        this.className=className;
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder extending(@NotNull String extendsClass){
        this.extendsClass=extendsClass;
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder extending(@NotNull Class<?> extendsClass){
        this.extendsClass=extendsClass.getCanonicalName();
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withInterface(@NotNull String extendsClass){
        this.interfaces.add(extendsClass);
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withInterface(@NotNull Class<?> extendsClass){
        this.interfaces.add(extendsClass.getCanonicalName());
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withImport(@NotNull String importClass){
        this.imports.add(importClass);
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withImport(@NotNull Class<?> importClass){
        this.imports.add(importClass.getCanonicalName());
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withFeaturePath(@NotNull String featurePath){
        this.cucumberOptions.put("features", String.format("\"%s\"", featurePath));
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withGlue(@NotNull String... glue){
        String glueValue="{\"" + String.join("\",\"",glue) + "\"}";
        this.cucumberOptions.put("glue", glueValue);
        return this;
    }

    @NotNull
    @Contract("_ -> this")
    public CucumberClassBuilder withTags(@NotNull String... tags){
        String glueValue="{\"" + String.join("\",\"",tags) + "\"}";
        this.cucumberOptions.put("tags", glueValue);
        return this;
    }

    public void print(){
        buildSystem();
        buildImports();
        buildCucumberOptions();
        buildBody();
        System.out.println(content.toString());
    }





}
