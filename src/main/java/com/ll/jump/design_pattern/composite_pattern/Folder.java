package com.ll.jump.design_pattern.composite_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈组合模式示例-文件夹〉
 *
 * @author LiLin
 * @date 2020/7/30 0030
 */
public class Folder {
    private List<Folder> sonFolderList;
    private String name;
    public Folder(String name){
        this.name = name;
        sonFolderList = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public void addSonFolder(Folder folder){
        sonFolderList.add(folder);
    }

    public void removeSonFolder(Folder folder){
        sonFolderList.remove(folder);
    }

    public List<Folder> getSonFolderList(){
        return this.sonFolderList;
    }

    public void printFolder(){
        printFolder(this, "");
    }

    private void printFolder(Folder folder, String space){
        System.out.println(space + folder.getName());
        if (folder.getSonFolderList() == null){
            return;
        }
        space = space + " ";
        for (Folder sonFolder : folder.getSonFolderList()) {
            printFolder(sonFolder, space);
        }
    }
}