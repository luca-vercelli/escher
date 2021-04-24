package org.gnu.escher.x11.enums;

public enum DrawableShape {
    CURSOR(0),
    TILE(1),
    STIPPLE(2);
    
    private int code;
    
    DrawableShape(int cd) {
        this.code =cd;
    }
    
    public int getCode() {
        return code;
    }
    
    public static DrawableShape getFamily(int code) {
        switch (code)
        {
            case 0: return DrawableShape.CURSOR;
            case 1: return DrawableShape.TILE;
            case 2: return DrawableShape.STIPPLE;
            
            default: return DrawableShape.CURSOR;
        }
    }
}