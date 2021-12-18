const PostImages = (props) => {

    const gallery = props.gallery

    switch (gallery.length) {
        case 1:
            return (
                <div>
                    <img
                        className="full"
                        src={gallery[0].url}
                        alt="image"
                    />
                </div>
            );
        case 2:
            return (
                <div>
                    <img
                        className="half1"
                        src={gallery[0].url}
                        alt="image"
                    />
                    <img
                        className="half2"
                        src={gallery[1].url}
                        alt="image"
                    />
                </div>
            )
        case 3:
            return (
                <div>
                    <img
                        className="half1"
                        src={gallery[0].url}
                        alt="image"
                    />
                    <img
                        className="merge-quarter1"
                        src={gallery[1].url}
                        alt="image"
                    />
                    <img
                        className="merge-quarter2"
                        src={gallery[2].url}
                        alt="image"
                    />
                </div>
            )
        case 4:
            return (
                <div>
                    <img
                        className="half1"
                        src={gallery[0].url}
                        alt="image"
                    />
                    <img
                        className="merge-quarter1"
                        src={gallery[1].url}
                        alt="image"
                    />
                    <img
                        className="quarter3"
                        src={gallery[2].url}
                        alt="image"
                    />
                    <img
                        className="quarter4"
                        src={gallery[3].url}
                        alt="image"
                    />
                </div>
            )
        default:
            return (
                <div>
                    <img
                        className="half1"
                        src={gallery[0].url}
                        alt="image"
                    />
                    <img
                        className="quarter1"
                        src={gallery[1].url}
                        alt="image"
                    />
                    <img
                        className="quarter2"
                        src={gallery[2].url}
                        alt="image"
                    />
                    <img
                        className="quarter3"
                        src={gallery[3].url}
                        alt="image"
                    />
                    <img
                        className="quarter4"
                        src={gallery[4].url}
                        alt="image"
                    />
                </div>
            )
    }

}

export default PostImages;