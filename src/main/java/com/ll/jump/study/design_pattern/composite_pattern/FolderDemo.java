package com.ll.jump.study.design_pattern.composite_pattern;

/**
 * 〈组合模式demo〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class FolderDemo {
    public static void main(String[] args) {
        Folder folder = new Folder("a");
        Folder foldera1 = new Folder("a1");
        Folder foldera11 = new Folder("a11");
        foldera1.addSonFolder(foldera11);
        Folder folderb1 = new Folder("b1");
        Folder folderb11 = new Folder("b11");
        folderb1.addSonFolder(folderb11);
        folder.addSonFolder(foldera1);
        folder.addSonFolder(folderb1);
        folder.printFolder();
    }
}