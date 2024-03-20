import {mount} from "@vue/test-utils";
import FeedbackView from "@/views/FeedbackView.vue";
import {beforeEach, describe, expect, it, vi} from "vitest";
import {createPinia, setActivePinia} from "pinia";

describe("FeedbackView test suite", () => {
    let wrapper;
    beforeEach(() => {
        setActivePinia(createPinia());
        wrapper = mount(FeedbackView);
    })

    it("Submit is not called when submit is pressed without all fields filled out.", async () => {
        const spy = vi.spyOn(wrapper.vm, "submit");
        await wrapper.find("#submit-button").trigger("click");
        expect(spy).toHaveBeenCalledTimes(0);
    });
});