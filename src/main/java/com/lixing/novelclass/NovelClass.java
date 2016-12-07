package com.lixing.novelclass;

/**
 * Created by Toephy on 2016.10.17 16:29
 */
public class NovelClass {
    private int cid;
    private int parentCid = -1; // -1表示没有父级分类
    private int classLevel = 1;
    private String className;
    private String englishName;

    public NovelClass() {
    }

    public NovelClass(int cid, int parentCid, String className, String englishName) {
        this.cid = cid;
        this.parentCid = parentCid;
        this.className = className;
        this.englishName = englishName;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public void setClassLevel() {
        if (parentCid < 0) {
            this.classLevel = 1;
        } else {

        }
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getParentCid() {
        return parentCid;
    }

    public void setParentCid(int parentCid) {
        this.parentCid = parentCid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
