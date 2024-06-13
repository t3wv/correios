package io.t3w.correios.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class T3WCorreiosResponseCustomPage {
    @JsonProperty("size")
    private Long size;

    @JsonProperty("numberElements")
    private Long numberElements;

    @JsonProperty("totalPages")
    private Long totalPages;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("count")
    private Long count;

    @JsonProperty("next")
    private Boolean next;

    @JsonProperty("previous")
    private Boolean previous;

    @JsonProperty("first")
    private Boolean first;

    @JsonProperty("last")
    private Boolean last;

    public T3WCorreiosResponseCustomPage() {
    }

    public T3WCorreiosResponseCustomPage(Long size, Long numberElements, Long totalPages, Long number, Long count, Boolean next, Boolean previous, Boolean first, Boolean last) {
        this.size = size;
        this.numberElements = numberElements;
        this.totalPages = totalPages;
        this.number = number;
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.first = first;
        this.last = last;
    }

    public Long getSize() {
        return size;
    }

    public T3WCorreiosResponseCustomPage setSize(Long size) {
        this.size = size;
        return this;
    }

    public Long getNumberElements() {
        return numberElements;
    }

    public T3WCorreiosResponseCustomPage setNumberElements(Long numberElements) {
        this.numberElements = numberElements;
        return this;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public T3WCorreiosResponseCustomPage setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Long getNumber() {
        return number;
    }

    public T3WCorreiosResponseCustomPage setNumber(Long number) {
        this.number = number;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public T3WCorreiosResponseCustomPage setCount(Long count) {
        this.count = count;
        return this;
    }

    public Boolean getNext() {
        return next;
    }

    public T3WCorreiosResponseCustomPage setNext(Boolean next) {
        this.next = next;
        return this;
    }

    public Boolean getPrevious() {
        return previous;
    }

    public T3WCorreiosResponseCustomPage setPrevious(Boolean previous) {
        this.previous = previous;
        return this;
    }

    public Boolean getFirst() {
        return first;
    }

    public T3WCorreiosResponseCustomPage setFirst(Boolean first) {
        this.first = first;
        return this;
    }

    public Boolean getLast() {
        return last;
    }

    public T3WCorreiosResponseCustomPage setLast(Boolean last) {
        this.last = last;
        return this;
    }
}
