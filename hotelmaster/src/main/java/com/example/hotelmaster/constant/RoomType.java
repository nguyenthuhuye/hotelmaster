package com.example.hotelmaster.constant;

import jakarta.persistence.AssociationOverride;

public enum RoomType {
    //phòng tiêu chuẩn, 2 người lớn
    STANDARD,
    //phòng cao cấp, 2 người lớn
    SUPERIOR,
    //phòng sang trọng, 2 người lớn
    DELUXE,
    //phòng hạng sang, 2 người lớn
    SUITE,
    //phòng gia đình, 4 người lớn
    FAMILY_ROOM
}
