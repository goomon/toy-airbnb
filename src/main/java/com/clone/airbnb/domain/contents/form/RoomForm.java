package com.clone.airbnb.domain.contents.form;

import com.clone.airbnb.domain.contents.model.Room;
import com.clone.airbnb.domain.contents.model.RoomKind;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RoomForm {

    private Long id;

    @NotBlank(message = "국가를 선택해야 합니다.")
    private String country;

    @NotBlank(message = "도시를 선택해야 합니다.")
    private String city;

    @Min(value = 1000, message = "1000원 이상이어야 합니다.")
    private Integer price;

    @NotNull(message = "방 개수를 입력해야 합니다.")
    private Integer rooms;

    @NotNull(message = "화장실 개수를 입력해야 합니다.")
    private Integer toilets;

    @NotBlank(message = "설명을 입력해야 합니다.")
    private String description;

    private String address;

    private Boolean petFriendly;

    private RoomKind roomKind;

    @NotNull(message = "호스트를 선택해야 합니다.")
    private Long ownerId;

    public RoomForm(Room room) {
        id = room.getId();
        country = room.getCountry();
        city = room.getCity();
        price = room.getPrice();
        rooms = room.getRooms();
        toilets = room.getToilets();
        description = room.getDescription();
        address = room.getAddress();
        petFriendly = room.getPetFriendly();
        roomKind = room.getRoomKind();
        ownerId = room.getOwner().getId();
    }
}
